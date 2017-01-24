package parser;
import model.*;

public class Constant extends Node {
    private double value;

    public Constant(double value) {
        this.value = value;
    }

    @Override
    public double evaluate(double y) {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
