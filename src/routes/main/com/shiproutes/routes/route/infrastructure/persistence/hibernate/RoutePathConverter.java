package com.shiproutes.routes.route.infrastructure.persistence.hibernate;

import javax.persistence.AttributeConverter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RoutePathConverter implements AttributeConverter<List<Double[]>, String> {
    @Override
    public String convertToDatabaseColumn(List<Double[]> attribute) {
        if (attribute == null) return null;
        if (attribute.isEmpty()) return "[]";
        return attribute.stream()
            .map(coord -> String.format("[%s, %s]", coord[0].toString(), coord[1].toString()))
            .collect(Collectors.joining(", ", "[", "]"));
    }

    @Override
    public List<Double[]> convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        if (dbData.equals("[]")) return new ArrayList<>();
        return Arrays.stream(dbData.substring(1, dbData.length() - 1).split("], \\["))
            .map(coord -> coord.replaceAll("[\\[\\]]", "").split(", "))
            .map(split -> new Double[]{Double.valueOf(split[0]), Double.valueOf(split[1])})
            .collect(Collectors.toCollection(ArrayList::new));
    }
}