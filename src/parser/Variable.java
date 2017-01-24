package parser;
import java.math.BigDecimal;

import model.*;


public class Variable extends Node {
    private String var;

    public Variable(String var) {
        this.var = var;
    }

    @Override
    public double evaluate(double y) {
        if (var.equals("y")) {
            return y;
        } else {
            System.out.println("Not 'Y' in expression");
            return 0;
        }
    }

    @Override
    public String toString() {
        return var;
    }
}
