package com.shiproutes.apps.backoffice.backend.controller.port_event;

import com.shiproutes.backoffice.port_event.application.ingest.IngestPortEventCommand;
import com.shiproutes.shared.domain.DomainError;
import com.shiproutes.shared.domain.bus.command.CommandBus;
import com.shiproutes.shared.domain.bus.query.QueryBus;
import com.shiproutes.shared.infrastructure.auth.AuthorizeAdmins;
import com.shiproutes.shared.infrastructure.spring.ApiController;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;

@RestController
@AuthorizeAdmins
public class IngestPortEventsPortController extends ApiController {

    private final JobLauncher jobLauncher;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    public IngestPortEventsPortController(QueryBus queryBus, CommandBus commandBus,
                                          JobLauncher jobLauncher, JobRepository jobRepository,
                                          @Qualifier("ingest-batch-transaction_manager") PlatformTransactionManager transactionManager) {
        super(queryBus, commandBus);
        this.jobLauncher = jobLauncher;
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }

    @PostMapping("/ingest/port-events")
    public void handle(@RequestParam(name = "file") MultipartFile file)
        throws IOException, JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException,
        JobParametersInvalidException, JobRestartException {

        Resource resource = new InputStreamResource(file.getInputStream());
        Job job = job(resource);
        jobLauncher.run(job, new JobParameters());
    }

    public Job job(Resource resource) {
        return new JobBuilder("eventsIngestionJob-" + Instant.now().toString())
            .repository(jobRepository)
            .start(step(resource))
            .build();
    }

    protected Step step(Resource resource) {
        return new StepBuilder("eventsIngestStep")
            .repository(jobRepository)
            .transactionManager(transactionManager)
            .<IngestPortEventCommand, IngestPortEventCommand>chunk(10)
            .reader(itemReader(resource))
            .writer(itemWriter())
            .faultTolerant()
            .build();
    }

    public FlatFileItemReader<IngestPortEventCommand> itemReader(Resource resource) {
        FlatFileItemReader<IngestPortEventCommand> reader = new FlatFileItemReader<>();

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        String[] tokens = {"locode", "portName", "longitude", "latitude", "imo", "shipName", "teus", "timestamp", "eventType"};
        tokenizer.setNames(tokens);

        reader.setLinesToSkip(1);

        reader.setResource(resource);

        DefaultLineMapper<IngestPortEventCommand> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSet -> new IngestPortEventCommand(
            fieldSet.readString("locode"),
            fieldSet.readString("portName"),
            fieldSet.readDouble("longitude"),
            fieldSet.readDouble("latitude"),
            fieldSet.readString("imo"),
            fieldSet.readString("shipName"),
            fieldSet.readInt("teus"),
            Instant.parse(fieldSet.readString("timestamp")),
            fieldSet.readString("eventType")
        ));
        reader.setLineMapper(lineMapper);

        return reader;
    }

    private ItemWriter<IngestPortEventCommand> itemWriter() {
        return items -> items.forEach(this::dispatch);
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return null;
    }

}
