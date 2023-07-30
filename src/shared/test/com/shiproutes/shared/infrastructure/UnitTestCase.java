package com.shiproutes.shared.infrastructure;

import com.shiproutes.shared.domain.DomainError;
import com.shiproutes.shared.domain.UuidGenerator;
import com.shiproutes.shared.domain.bus.event.DomainEvent;
import com.shiproutes.shared.domain.bus.event.EventBus;
import com.shiproutes.shared.domain.bus.query.Query;
import com.shiproutes.shared.domain.bus.query.QueryBus;
import com.shiproutes.shared.domain.bus.query.QueryHandlerExecutionError;
import com.shiproutes.shared.domain.bus.query.Response;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;


@ActiveProfiles("test")
public abstract class UnitTestCase {
    protected EventBus eventBus;
    protected QueryBus queryBus;
    protected UuidGenerator uuidGenerator;

    @BeforeEach
    protected void setUp() {
        eventBus = mock(EventBus.class);
        queryBus = mock(QueryBus.class);
        uuidGenerator = mock(UuidGenerator.class);
    }

    public void shouldHavePublished(List<DomainEvent> domainEvents) {
        verify(eventBus, atLeastOnce()).publish(domainEvents);
    }

    public void shouldHavePublished(DomainEvent domainEvent) {
        shouldHavePublished(Collections.singletonList(domainEvent));
    }

    public void shouldNotHavePublished(List<DomainEvent> domainEvents) {
        verify(eventBus, never()).publish(domainEvents);
    }

    public void shouldGenerateUuid(String uuid) {
        when(uuidGenerator.generate()).thenReturn(uuid);
    }

    public void shouldGenerateUuids(String uuid, String... others) {
        when(uuidGenerator.generate()).thenReturn(uuid, others);
    }

    public void shouldAsk(Query query, Response response) {
        when(queryBus.ask(query)).thenReturn(response);
    }

    public void shouldFailOnAsk(Query query, DomainError error) {
        when(queryBus.ask(query)).thenThrow(new QueryHandlerExecutionError(error));
    }
}
