package pl.kurs.zad.util;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import pl.kurs.zad.model.Circle;
import pl.kurs.zad.model.Rectangle;
import pl.kurs.zad.model.Square;
import pl.kurs.zad.model.Shape;
import java.io.IOException;

public class ShapeSerializer extends StdSerializer<Shape> {

    protected ShapeSerializer(Class t) {
        super(t);
    }

    @Override
    public void serialize(Shape shape, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("type", shape.getType());
        if (shape instanceof Circle) {
            Circle circle = (Circle) shape;
            jsonGenerator.writeNumberField("radius", circle.getRadius());
        } else if (shape instanceof Square) {
            Square square = (Square) shape;
            jsonGenerator.writeNumberField("sideLength", square.getSideLength());
        } else if (shape instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) shape;
            jsonGenerator.writeNumberField("width", rectangle.getWidth());
            jsonGenerator.writeNumberField("height", rectangle.getHeight());
        }
        jsonGenerator.writeEndObject();
    }
}

