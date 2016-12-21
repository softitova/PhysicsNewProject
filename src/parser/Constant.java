package parser;
import model.*;
/**
 * Created by vadim on 26.11.16.
 */
public class Constant extends Node {
    private double value;

    public Constant(double value) {
        this.value = value;
    }

    @Override
    public double evaluate(double x, double y) {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
