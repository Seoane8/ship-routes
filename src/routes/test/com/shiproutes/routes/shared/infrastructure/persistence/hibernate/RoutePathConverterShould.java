package com.shiproutes.routes.shared.infrastructure.persistence.hibernate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoutePathConverterShould {

    RoutePathConverter converter;

    private static Object[] params() {
        return new Object[]{
            new Object[]{"[]", List.of()},
            new Object[]{"[[1.0, 2.0], [3.0, 4.0]]", List.of(List.of(1.0, 2.0), List.of(3.0, 4.0))}
        };
    }

    @BeforeEach
    void setUp() {
        this.converter = new RoutePathConverter();
    }

    @ParameterizedTest
    @MethodSource("params")
    void convert_from_list_to_json_array_string(String expected, List<List<Double>> attribute) {
        String actual = converter.convertToDatabaseColumn(attribute);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("params")
    void convert_from_json_array_string_to_list(String dbData, List<List<Double>> expected) {
        List<List<Double>> actual = converter.convertToEntityAttribute(dbData);
        assertEquals(expected, actual);
    }


}
