package model;

/**
 * Created by vadim on 28.11.16.
 */
public class Point {
    public int x, y;

    public Point() {}

    @Override
    public String toString() {
        return "{x=" + x + ", y=" + y + "}";
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;


    }
}
