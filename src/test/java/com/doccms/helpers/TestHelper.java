package com.doccms.helpers;

import java.security.SecureRandom;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.IntStream;

public class TestHelper {
    private static final String digitsAndLetters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final Random random = new SecureRandom();

    public static String getRandomId(String prefix) {
        return prefix + getRandomId();
    }

    public static Long getRandomId() {
        return random.nextLong(1, 999999);
    }

    public static String getRandomText(int length) {
        return IntStream.range(0, length)
                        .mapToObj(i -> digitsAndLetters.charAt(random.nextInt(digitsAndLetters.length())))
                        .map(Objects::toString)
                        .reduce("", String::concat);
    }

    public static <T extends Enum<T>> T getRandomClass(Class<T> clazz) {
        final T[] values = clazz.getEnumConstants();
        return values[random.nextInt(values.length)];
    }

    public static List<Object> getRandomListOfStrings() {
        return List.of(
            getRandomText(random.nextInt(20)),
            getRandomText(random.nextInt(20)),
            getRandomText(random.nextInt(20)));
    }
}
