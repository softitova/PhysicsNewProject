package model;
import parser.L;

import java.util.HashMap;

/**
 * Created by vadim on 26.11.16.
 */
public class Node {
    private Node left, right;
    private L oper;

    private static HashMap<L, String> mp;

    private static final String PLUS = "+";

    public static final String MINUS = "-";

    public static final String MULT = "*";

    public static final String DIV = "/";

    public static final String POWER = "^";

    static {
        mp = new HashMap<>();
        mp.put(L.plus, PLUS);
        mp.put(L.minus, MINUS);
        mp.put(L.mult, MULT);
        mp.put(L.div, DIV);
        mp.put(L.power, POWER);
    }

    public Node() {}
    public Node(Node left, Node right, L oper) {
        this.left = left;
        this.right = right;
        this.oper = oper;
    }

    public double evaluate(double x, double y) {
        switch (oper) {
            case plus:
                return left.evaluate(x, y) + right.evaluate(x, y);
            case minus:
                return left.evaluate(x, y) - right.evaluate(x, y);
            case mult:
                return left.evaluate(x, y) * right.evaluate(x, y);
            case div:
                return left.evaluate(x, y) / right.evaluate(x, y);
            case power:
                return Math.pow(left.evaluate(x, y), right.evaluate(x, y));
            default:
                return 0;
        }
    }

    public String toString() {
        return "(" + left.toString() + mp.get(oper) + right.toString() + ")";
    }
}
