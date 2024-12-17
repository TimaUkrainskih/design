package ru.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;

public class SerializationJSONRun {
    public static void main(String[] args) throws Exception {
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
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isAlive", object.isActive());
        jsonObject.put("count", object.getCount());
        jsonObject.put("name", object.getName());
        jsonObject.put("nested", new JSONObject(object.getNestedObject()));
        jsonObject.put("tags", object.getTags());
        System.out.println(jsonObject);
        System.out.println(new JSONObject(object));

    }
}
