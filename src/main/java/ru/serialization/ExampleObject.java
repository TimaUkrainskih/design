package ru.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.*;
import org.json.JSONObject;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "exampleObject")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExampleObject {
    private boolean isActive;
    private int count;
    private String name;
    private NestedObject nestedObject;
    @XmlElementWrapper(name = "tags")
    @XmlElement(name = "tag")
    private String[] tags;

    public ExampleObject() {

    }

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

    public boolean isActive() {
        return isActive;
    }

    public int getCount() {
        return count;
    }

    public String getName() {
        return name;
    }

    public NestedObject getNestedObject() {
        return nestedObject;
    }

    public String[] getTags() {
        return tags;
    }
}