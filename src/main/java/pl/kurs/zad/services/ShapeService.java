package pl.kurs.zad.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.kurs.zad.exception.InvalidShapeException;
import pl.kurs.zad.model.Shape;
import pl.kurs.zad.util.ObjectMapperHolder;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ShapeService {

    private final ObjectMapper objectMapper = ObjectMapperHolder.INSTANCE.getObjectMapper();

    public Shape getShapeWithBiggestArea(List<Shape> shapesList) throws InvalidShapeException {
        return Optional.ofNullable(shapesList)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .max(Comparator.comparingDouble(Shape::calculateArea))
                .orElseThrow(() -> new InvalidShapeException("No such shape exists"));
    }

    public Shape getShapeWithBiggestPerimeter(List<Shape> shapesList, String shapeType) throws InvalidShapeException {
        return Optional.ofNullable(shapesList)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .filter(x -> x.getClass().getSimpleName().equalsIgnoreCase(shapeType))
                .max(Comparator.comparingDouble(Shape::calculatePerimeter))
                .orElseThrow(() -> new InvalidShapeException("No such shape exists"));
    }

    public void exportShapesToJson(List<Shape> shapeList, String pathName) throws IOException {
        objectMapper.writeValue(new File(pathName), shapeList);
    }

    public List<Shape> importShapesFromJson(String pathName) throws IOException {
        Path path = Paths.get(pathName);
        List<Shape> shapes = new ArrayList<>();
        JsonNode rootNode = objectMapper.readTree(path.toFile());
        for (JsonNode shapeNode : rootNode) {
            Shape shape = objectMapper.treeToValue(shapeNode, Shape.class);
            shapes.add(shape);
        }
        return shapes;
    }


}
