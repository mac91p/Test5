package pl.kurs.zad.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.Objects;

@JsonTypeName("Circle")
public class Circle implements Shape {

    private double radius;

    private Circle(double radius) {
        this.radius = radius;
    }

    @JsonCreator
    static Circle create(@JsonProperty("radius") double radius) {
        return new Circle(radius);
    }

    @JsonProperty("radius")
    public double getRadius() {
        return radius;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Circle circle = (Circle) o;
        return Double.compare(circle.radius, radius) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(radius);
    }

    @Override
    public double calculateArea() {
        return PI_NUMBER * Math.pow(radius, 2);
    }

    @Override
    public double calculatePerimeter() {
        return 2 * PI_NUMBER * radius;
    }


    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                '}';
    }
}
