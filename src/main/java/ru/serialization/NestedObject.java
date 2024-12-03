package ru.serialization;

public class NestedObject {
    private String description;
    private double value;

    public NestedObject(String description, double value) {
        this.value = value;
        this.description = description;
    }

    @Override
    public String toString() {
        return "NestedObject{" +
                "description='" + description + '\'' +
                ", value=" + value +
                '}';
    }
}
