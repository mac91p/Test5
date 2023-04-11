package pl.kurs.zad.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ShapeFactoryTest {

    private Square square;
    private Rectangle rectangle;
    private Circle circle;

    @Before
    public void setUp() {
        square = ShapeFactory.createSquare(10);
        rectangle = ShapeFactory.createRectangle(15,20);
        circle = ShapeFactory.createCircle(15);
    }

    @Test
    public void shouldReturnTrueFromCreateSquare() {
        Square squareTest = ShapeFactory.createSquare(10);
        assertEquals(square, squareTest);
    }

    @Test
    public void shouldReturnTrueFromCreateRectangle() {
        Rectangle rectangleTest = ShapeFactory.createRectangle(15,20);
        assertEquals(rectangle, rectangleTest);
    }

    @Test
    public void shouldReturnTrueFromCreateCircle() {
        Circle circleTest = ShapeFactory.createCircle(15);
        assertEquals(circle, circleTest);
    }


}