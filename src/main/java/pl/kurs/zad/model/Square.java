package pl.kurs.zad.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.Objects;

@JsonTypeName("Square")
public class Square implements Shape {

    private double sideLength;

    private Square(double sideLength) {
        this.sideLength = sideLength;
    }
    /**
     * Celowo użyty modifikator defaultowy
     * */
    @JsonCreator
    static Square create(@JsonProperty("sideLength") double sideLength) {
        return new Square(sideLength);
    }

    @JsonProperty("side_length")
    public double getSideLength() {
        return sideLength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return Double.compare(square.sideLength, sideLength) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sideLength);
    }

    @Override
    public double calculateArea() {
        return sideLength * sideLength;
    }

    @Override
    public double calculatePerimeter() {
        return 4 * sideLength;
    }

    @Override
    public String toString() {
        return "Square{" +
                "sideLength=" + sideLength +
                '}';
    }
}

