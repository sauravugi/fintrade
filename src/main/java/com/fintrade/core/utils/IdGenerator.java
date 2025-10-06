package com.fintrade.core.utils;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.security.SecureRandom;

public class IdGenerator {

    private static final SecureRandom random = new SecureRandom();
    private static final String ALPHANUM = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String generateRawId(String type) {
        LocalDateTime now = LocalDateTime.now();

        String datePart = now.format(DateTimeFormatter.ofPattern("ddMMMyy")).toUpperCase();
        String rand1 = randomAlphaNumeric(5);
        String timePart = now.format(DateTimeFormatter.ofPattern("HHmmss"));
        String rand2 = randomAlphaNumeric(5);

        return type + datePart + rand1 + timePart + rand2;
    }

    private static String randomAlphaNumeric(int length) {
        StringBuilder sb = new StringBuilder(length);
        for(int i = 0; i < length; i++) {
            sb.append(ALPHANUM.charAt(random.nextInt(ALPHANUM.length())));
        }
        return sb.toString();
    }
}
