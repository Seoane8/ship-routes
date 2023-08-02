package com.shiproutes.backoffice.port_event.infrastructure;

import com.shiproutes.backoffice.port_event.domain.PortCreator;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.ports.Coordinates;
import com.shiproutes.shared.domain.ports.PortId;
import com.shiproutes.shared.infrastructure.config.Parameter;
import com.shiproutes.shared.infrastructure.config.ParameterNotExist;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.HashMap;

@Service
public class RestPortCreator implements PortCreator {

    private final RestTemplate restTemplate;
    private final String url;

    public RestPortCreator(RestTemplate restTemplate, Parameter config) throws ParameterNotExist {
        this.restTemplate = restTemplate;
        this.url = config.get("INGEST_PORTS_SERVICE_URL");
    }

    @Override
    public void create(PortId id, String locode, String portName, Coordinates coordinates) {
        HashMap<String, Serializable> body = new HashMap<>() {{
            put("id", id.value());
            put("name", portName);
            put("locode", locode);
            put("latitude", coordinates.latitude().value());
            put("longitude", coordinates.longitude().value());
        }};
        restTemplate.postForObject(url + "/ports", body, Void.class);
    }
}
