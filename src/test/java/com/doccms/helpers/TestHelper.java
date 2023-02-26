package com.doccms.helpers;

import java.security.SecureRandom;
import java.util.Objects;
import java.util.Random;
import java.util.stream.IntStream;

public class TestHelper {

    private static final String letters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final Random random = new SecureRandom();

    public static String getRandomId(String prefix) {
        return prefix + getRandomId();
    }

    public static Long getRandomId() {
        return random.nextLong(1, 999999);
    }

    public static String getRandomText(int length) {
        return IntStream.range(0, length)
                        .mapToObj(i -> letters.charAt(random.nextInt(letters.length())))
                        .map(Objects::toString)
                        .reduce("", String::concat);
    }
}
