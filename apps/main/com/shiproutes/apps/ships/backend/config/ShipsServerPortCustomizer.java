package com.shiproutes.apps.ships.backend.config;

import com.shiproutes.shared.infrastructure.config.Parameter;
import com.shiproutes.shared.infrastructure.config.ParameterNotExist;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

@Component
public final class ShipsServerPortCustomizer implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {
    private final Parameter param;

    public ShipsServerPortCustomizer(Parameter param) {
        this.param = param;
    }

    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        try {
            factory.setPort(param.getInt("SHIP_BACKEND_SERVER_PORT"));
        } catch (ParameterNotExist parameterNotExist) {
            parameterNotExist.printStackTrace();
        }
    }
}
