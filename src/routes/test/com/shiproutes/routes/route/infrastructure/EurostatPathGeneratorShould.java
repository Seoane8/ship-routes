package com.shiproutes.routes.route.infrastructure;

import com.shiproutes.routes.shared.domain.RoutePath;
import com.shiproutes.shared.domain.ports.Coordinates;
import com.shiproutes.shared.domain.ports.CoordinatesMother;
import com.shiproutes.shared.domain.ports.Latitude;
import com.shiproutes.shared.domain.ports.Longitude;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EurostatPathGeneratorShould {

    EurostatPathGenerator eurostatPathGenerator = new EurostatPathGenerator();

    static Object[] params() {
        return new Object[]{
            new Object[]{CoordinatesMother.random(), CoordinatesMother.random()},
            new Object[]{
                new Coordinates(new Latitude(-10.31509), new Longitude(150.45742)),
                new Coordinates(new Latitude(-38.71666666666667), new Longitude(-62.28333333333333))
            },
            new Object[]{
                new Coordinates(new Latitude(17.116666666666667), new Longitude(-61.85)),
                new Coordinates(new Latitude(-4.75), new Longitude(136.55))
            },
            new Object[]{
                new Coordinates(new Latitude(12.4825), new Longitude(-87.17304)),
                new Coordinates(new Latitude(34.16297), new Longitude(132.22))
            },
        };
    }

    @ParameterizedTest
    @MethodSource("params")
    void generate_a_path_between_two_coordinates(Coordinates origin, Coordinates destination) {
        RoutePath path = eurostatPathGenerator.generate(origin, destination);
        assertEquals(origin, path.origin());
        assertEquals(destination, path.destination());
    }
}
