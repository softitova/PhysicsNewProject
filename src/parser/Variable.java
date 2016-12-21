package parser;
import java.math.BigDecimal;

import model.*;

/**
 * Created by vadim on 26.11.16.
 */
public class Variable extends Node {
    private String var;

    public Variable(String var) {
        this.var = var;
    }

    @Override
    public double evaluate(double x, double y) {
        if (var.equals("x")) {
            return x;
        } else {
            return y;
        }
    }

    @Override
    public String toString() {
        return var;
    }
}
