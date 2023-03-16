package com.shiproutes.apps.routes.backend;

import com.shiproutes.shared.domain.Service;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import java.util.HashMap;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@ComponentScan(
    includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Service.class),
    value = {"com.shiproutes.shared", "com.shiproutes.routes", "com.shiproutes.apps.routes.backend"}
)
public class RoutesApplication {
    public static HashMap<String, Class<?>> commands() {
        return new HashMap<String, Class<?>>() {{
        }};
    }
}