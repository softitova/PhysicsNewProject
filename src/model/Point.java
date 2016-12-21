package model;

/**
 * Created by vadim on 28.11.16.
 */
public class Point {
    public double x, y;

    public Point() {}

    @Override
    public String toString() {
        return "{x=" + x + ", y=" + y + "}";
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
