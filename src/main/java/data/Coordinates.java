package data;

public class Coordinates {
    private double x;
    private Float y; //Максимальное значение поля: 262, Поле не может быть null

    public Coordinates(double x, Float y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public Float getY() {
        return y;
    }
}
