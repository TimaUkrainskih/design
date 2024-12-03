package ru.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class ExampleObject {

    private boolean isActive;
    private int count;
    private String name;
    private NestedObject nestedObject;
    private String[] tags;

    public ExampleObject(boolean isActive, int count, String name, NestedObject nestedObject, String[] tags) {
        this.isActive = isActive;
        this.count = count;
        this.name = name;
        this.nestedObject = nestedObject;
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "ExampleObject{" +
                "isActive=" + isActive +
                ", count=" + count +
                ", name='" + name + '\'' +
                ", nestedObject=" + nestedObject +
                ", tags=" + Arrays.toString(tags) +
                '}';
    }

    public static void main(String[] args) {
        ExampleObject object = new ExampleObject(true, 5, "Name",
                new NestedObject("Description", 5.0), new String[]{"Tag1", "Tag2"});
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(object));
        final String objectString =
                "{"
                        + "\"isActive\": false,"
                        + "\"count\": 45,"
                        + "\"name\": \"ExampleName\","
                        + "\"nestedObject\":"
                        + "{"
                        + "\"description\": \"Description\","
                        + "\"value\": 5.8"
                        + "},"
                        + "\"tags\":"
                        + "[\"Tags1\",\"Tags2\", \"Tags3\"]"
                        + "}";

        ExampleObject jsonToObject = gson.fromJson(objectString, ExampleObject.class);
        System.out.println(jsonToObject);
    }
}