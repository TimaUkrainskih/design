package ru.serialization;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "nestedObject")
@XmlAccessorType(XmlAccessType.FIELD)
public class NestedObject {
    private String description;
    private double value;

    public NestedObject() {

    }

    public NestedObject(String description, double value) {
        this.value = value;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "NestedObject{" +
                "description='" + description + '\'' +
                ", value=" + value +
                '}';
    }
}
