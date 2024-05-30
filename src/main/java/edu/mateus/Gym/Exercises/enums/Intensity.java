package edu.mateus.Gym.Exercises.enums;

import java.util.Arrays;

public enum Intensity {
    ALTA,
    MEDIA,
    BAIXA;


    public static Intensity fromFront(String field) {
        return Arrays.stream(Intensity.values())
                .filter(intensity -> intensity.name().equalsIgnoreCase(field))
                .findFirst()
                .orElse(null);
    }
}