package ru.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.json.JSONObject;

import java.io.StringReader;
import java.io.StringWriter;

public class SerializationRun {
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
        JAXBContext context = JAXBContext.newInstance(ExampleObject.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(object, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            ExampleObject result = (ExampleObject) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
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
