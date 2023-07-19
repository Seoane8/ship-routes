package com.shiproutes.routes.route.domain;

import com.shiproutes.shared.domain.ports.Coordinates;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class RoutePath extends ArrayList<Coordinates> {

    public RoutePath(@NotNull Collection<? extends Coordinates> c) {
        super(c);
    }

    public static Collector<Coordinates, Object, RoutePath> collector() {
        return Collectors.collectingAndThen(Collectors.toList(), RoutePath::new);
    }

    public Coordinates origin() {
        return this.get(0);
    }

    public Coordinates destination() {
        return this.get(this.size() - 1);
    }

    public RoutePath reverse() {
        RoutePath reversedPath = new RoutePath(this);
        Collections.reverse(reversedPath);
        return reversedPath;
    }
}
