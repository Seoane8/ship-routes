package com.shiproutes.routes.route.application.create;

import com.shiproutes.shared.domain.bus.command.Command;

import java.util.List;
import java.util.Objects;

public class CreateRouteCommand implements Command {

    private final String id;
    private final String origin;
    private final String destination;
    private final List<List<Double>> path;

    public CreateRouteCommand(String id, String origin, String destination, List<List<Double>> path) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.path = path;
    }

    public String id() {
        return id;
    }

    public String origin() {
        return origin;
    }

    public String destination() {
        return destination;
    }

    public List<List<Double>> path() {
        return path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateRouteCommand)) return false;
        CreateRouteCommand that = (CreateRouteCommand) o;
        return Objects.equals(id, that.id) && Objects.equals(origin, that.origin)
            && Objects.equals(destination, that.destination) && Objects.equals(path, that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, origin, destination, path);
    }
}
