package pl.kurs.zad.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import pl.kurs.zad.model.Shape;

public enum ObjectMapperHolder {

    INSTANCE;

    private final ObjectMapper objectMapper;

    ObjectMapperHolder() {
        this.objectMapper = create();
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    private static ObjectMapper create() {
        ObjectMapper mapper = new ObjectMapper();
        ShapeSerializer shapeSerializer = new ShapeSerializer(Shape.class);
        SimpleModule sm1 = new SimpleModule("Shape Serializer");
        sm1.addSerializer(Shape.class, shapeSerializer);
        ShapeDeserializer shapeDeserializer = new ShapeDeserializer(Shape.class);
        SimpleModule sm2 = new SimpleModule("Shape Deserializer");
        sm2.addDeserializer(Shape.class, shapeDeserializer);
        mapper.registerModules(sm1,sm2);
        return mapper;
    }
}
