package com.company.cursach;

public enum Position {
    ASSISTANT("Ассистент"),
    SENIOR_ASSISTANT("Старший ассистент"),
    TEACHER("Преподаватель"),
    SENIOR_LECTURER("Старший лектор"),
    ASSISTANT_PROFESSOR("Ассистент профессора"),
    PROFESSOR("Профессор");

    private final String name;

    Position(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
