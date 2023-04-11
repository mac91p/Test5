package pl.kurs.zad.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import pl.kurs.zad.model.Shape;
import pl.kurs.zad.model.ShapeFactory;

import java.io.IOException;

public class ShapeDeserializer extends StdDeserializer<Shape> {

    protected ShapeDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Shape deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        String type = jsonNode.get("type").asText();
        switch (type) {
            case "Square" -> {
                double sideLength = jsonNode.get("sideLength").asDouble();
                return ShapeFactory.createSquare(sideLength);
            }
            case "Circle" -> {
                double radius = jsonNode.get("radius").asDouble();
                return ShapeFactory.createCircle(radius);
            }
            case "Rectangle" -> {
                double width = jsonNode.get("width").asDouble();
                double height = jsonNode.get("height").asDouble();
                return ShapeFactory.createRectangle(width, height);
            }
            default -> throw new JsonParseException(jsonParser, "Unknown shape: " + type);
        }
    }
}
