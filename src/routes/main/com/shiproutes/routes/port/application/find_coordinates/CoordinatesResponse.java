package com.shiproutes.routes.port.application.find_coordinates;

import com.shiproutes.shared.domain.bus.query.Response;
import com.shiproutes.shared.domain.ports.Coordinates;

import java.util.Objects;

public class CoordinatesResponse implements Response {

    private final Double latitude;

    private final Double longitude;

    public CoordinatesResponse(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static CoordinatesResponse fromEntity(Coordinates coordinates) {
        return new CoordinatesResponse(coordinates.latitude().value(), coordinates.longitude().value());
    }

    public Double latitude() {
        return latitude;
    }

    public Double longitude() {
        return longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CoordinatesResponse)) return false;
        CoordinatesResponse that = (CoordinatesResponse) o;
        return Objects.equals(latitude, that.latitude) && Objects.equals(longitude, that.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }
}
