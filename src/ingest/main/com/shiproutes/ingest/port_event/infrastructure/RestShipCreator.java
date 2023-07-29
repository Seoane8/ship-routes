package com.shiproutes.ingest.port_event.infrastructure;

import com.shiproutes.ingest.port_event.domain.ShipCreator;
import com.shiproutes.shared.domain.IMO;
import com.shiproutes.shared.infrastructure.config.Parameter;
import com.shiproutes.shared.infrastructure.config.ParameterNotExist;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.HashMap;

public class RestShipCreator implements ShipCreator {

    private final RestTemplate restTemplate;
    private final String url;

    public RestShipCreator(RestTemplate restTemplate, Parameter config) throws ParameterNotExist {
        this.restTemplate = restTemplate;
        this.url = config.get("INGEST_SHIPS_SERVICE_URL");
    }

    @Override
    public void create(IMO imo, String shipName, Integer teus) {
        HashMap<String, Serializable> body = new HashMap<>() {{
            put("imo", imo.value());
            put("name", shipName);
            put("teus", teus);
        }};
        restTemplate.postForObject(url + "/ships", body, Void.class);
    }
}
