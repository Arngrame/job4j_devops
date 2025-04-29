package ru.job4j.devops.enums;

public enum CalcEventType {

    ADDITION("Сложение"),
    SUBTRACTION("Вычитание"),
    MULTIPLICATION("Умножение"),
    DIVISION("Деление");

    private final String name;

    private CalcEventType(String name) {
        this.name = name;
    }
}
