package pl.kurs.zad.model;

import java.util.Objects;

public class Square implements Shape {

    private double sideLength;

    private Square(double sideLength) {
        this.sideLength = sideLength;
    }
    /**
     * Celowo użyty modifikator defaultowy
     * */
    static Square create(double sideLength) {
        return new Square(sideLength);
    }

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
    public String getType() {
        return getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return "Square{" +
                "sideLength=" + sideLength +
                '}';
    }
}

