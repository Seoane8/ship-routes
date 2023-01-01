package com.shiproutes.ships.ship.domain;

import com.shiproutes.shared.domain.IntegerMother;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class IMOMother {

    public static IMO random() {
        Integer[] digits = IntStream.range(0, 6).mapToObj(i -> IntegerMother.random()).toArray(Integer[]::new);
        int product = 7 * digits[0] + 6 * digits[1] + 5 * digits[2] + 4 * digits[3] + 3 * digits[4] + 2 * digits[5];
        int lastDigit = product % 10;
        String imo = Arrays.stream(digits).map(Object::toString).collect(Collectors.joining("")) + lastDigit;
        return new IMO(imo);
    }
}
