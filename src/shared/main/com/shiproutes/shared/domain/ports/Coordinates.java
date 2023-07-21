package com.shiproutes.shared.domain.ports;

import java.util.Objects;

public final class Coordinates {
    private final Latitude latitude;
    private final Longitude longitude;

    public Coordinates(Latitude latitude, Longitude longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Latitude latitude() {
        return latitude;
    }

    public Longitude longitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return String.format("[%s, %s]", latitude, longitude);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinates)) return false;
        Coordinates that = (Coordinates) o;
        return Objects.equals(latitude, that.latitude) && Objects.equals(longitude, that.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }
}
