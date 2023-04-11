package pl.kurs.zad.services;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import pl.kurs.zad.exception.InvalidShapeException;
import pl.kurs.zad.model.Shape;
import pl.kurs.zad.model.ShapeFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class ShapeServiceTest {

    private ShapeService shapeService;
    private List<Shape> shapeList;

    @Before
    public void setUp() {
        shapeService = new ShapeService();
        shapeList = new ArrayList<>();
        shapeList.add(ShapeFactory.createCircle(10));
        shapeList.add(ShapeFactory.createRectangle(15, 20));
        shapeList.add(ShapeFactory.createSquare(12.8));
        shapeList.add(ShapeFactory.createSquare(15));
        shapeList.add(null);
    }


    @Test
    public void shouldGetShapeWithBiggestAreaAndReturnCircleWith10Radius() throws InvalidShapeException {
        Shape biggestArea = shapeService.getShapeWithBiggestArea(shapeList);
        assertSame(shapeList.get(0), biggestArea);
    }

    @Test
    public void shouldGetShapeWithBiggestPerimeterAndReturnSquareWith15SideLength() throws InvalidShapeException {
        Shape biggestPerimeter = shapeService.getShapeWithBiggestPerimeter(shapeList, "square");
        assertSame(shapeList.get(3), biggestPerimeter);
    }

    @Test
    public void shouldReturnJsonStringFromList() throws IOException {
        String pathName = "src/main/resources/shapes.json";
        String expectedOutput = "[{\"type\":\"Circle\",\"radius\":10.0},{\"type\":\"Rectangle\",\"width\":15.0,\"" +
                "height\":20.0},{\"type\":\"Square\",\"sideLength\":12.8},{\"type\":\"Square\",\"sideLength\":15.0},null]";
        shapeService.exportShapesToJson(shapeList, pathName);
        String actualOutput = readFileContent(pathName);
        assertEquals(expectedOutput, actualOutput);

    }

    @Test
    public void shouldReturnShapeListFromJsonFile() throws IOException {
        String pathName = "src/main/resources/shapes.json";
        List<Shape> jsonShapes = shapeService.importShapesFromJson(pathName);
        assertEquals(shapeList, jsonShapes);
    }

    @Test
    public void shouldThrowInvalidShapeExceptionFromGetShapeWithBiggestAreaWhereListIsNull() {
        Exception e = assertThrows(InvalidShapeException.class, () -> shapeService.getShapeWithBiggestArea(null));
        Assertions.assertThat(e)
                .isExactlyInstanceOf(InvalidShapeException.class)
                .hasMessage("No such shape exists")
                .hasNoCause()
                .hasFieldOrProperty("message");
    }

    @Test
    public void shouldThrowInvalidShapeExceptionFromGetShapeWithBiggestPerimeterWhereShapeIsIncorrect() {
        Exception e = assertThrows(InvalidShapeException.class, () -> shapeService.getShapeWithBiggestPerimeter(shapeList, "shape"));
        Assertions.assertThat(e)
                .isExactlyInstanceOf(InvalidShapeException.class)
                .hasMessage("No such shape exists")
                .hasNoCause()
                .hasFieldOrProperty("message");
    }

    @Test(expected = IOException.class)
    public void shouldThrowIOExceptionFromExportToJsonWithInvalidPath() throws IOException {
        String pathName = "src/main/res/shapes.json";
        shapeService.exportShapesToJson(shapeList, pathName);
    }

    @Test(expected = IOException.class)
    public void shouldThrowIOExceptionFromImportShapesToJsonWithInvalidPath() throws  IOException {
        String pathName = "src/main/resources/shaes.json";
        shapeService.importShapesFromJson(pathName);
    }

    private String readFileContent(String fileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(fileName));
        return String.join("\n", lines);
    }
}