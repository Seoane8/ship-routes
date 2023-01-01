package com.shiproutes.apps.ports.backend;

import com.shiproutes.shared.domain.Service;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import java.util.HashMap;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@ComponentScan(
    includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Service.class),
    value = {"com.shiproutes.shared", "com.shiproutes.ports", "com.shiproutes.apps.ports.backend"}
)
public class PortsApplication {

    public static HashMap<String, Class<?>> commands() {
        return new HashMap<String, Class<?>>() {{
        }};
    }
}
