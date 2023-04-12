package pl.kurs.zad.model;

import java.util.HashMap;
import java.util.Map;

public class ShapeFactory {

    private static final Map<String, Shape> shapesCache = new HashMap<>();


    public static Square createSquare(double sideLength) {
        String key = "square_" + sideLength;
        if (shapesCache.containsKey(key)) {
            return (Square) shapesCache.get(key);
        } else {
            Square square = Square.create(sideLength);
            shapesCache.put(key, square);
            return square;
        }
    }

    public static Rectangle createRectangle(double width, double length) {
        String key = "rectangle_" + width + "_" + length;
        if (shapesCache.containsKey(key)) {
            return (Rectangle) shapesCache.get(key);
        } else {
            Rectangle rectangle = Rectangle.create(width, length);
            shapesCache.put(key, rectangle);
            return rectangle;
        }
    }

    public static Circle createCircle(double radius) {
        String key = "circle_" + radius;
        if (shapesCache.containsKey(key)) {
            return (Circle) shapesCache.get(key);
        } else {
            Circle circle = Circle.create(radius);
            shapesCache.put(key, circle);
            return circle;
        }
    }
}

