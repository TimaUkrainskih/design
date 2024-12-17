package ru.serialization;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.StringReader;
import java.io.StringWriter;

public class SerializationXMLRun {
    public static void main(String[] args) throws Exception {
        ExampleObject object = new ExampleObject(true, 5, "Name",
                new NestedObject("Description", 5.0), new String[]{"Tag1", "Tag2"});
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
    }
}
