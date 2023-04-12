package pl.kurs.zad.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.Objects;

@JsonTypeName("Rectangle")
public class Rectangle implements Shape {

    private double width;
    private double height;

    private Rectangle(double width, double length) {
        this.width = width;
        this.height = length;
    }
    @JsonCreator
    static Rectangle create(@JsonProperty("width") double width, @JsonProperty("length") double length) {
        return new Rectangle(width, length);
    }
    @JsonProperty("width")
    public double getWidth() {
        return width;
    }
    @JsonProperty("height")
    public double getHeight() {
        return height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return Double.compare(rectangle.width, width) == 0 && Double.compare(rectangle.height, height) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height);
    }

    @Override
    public double calculateArea() {
        return width * height;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * width + 2 * height;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", length=" + height +
                '}';
    }
}
