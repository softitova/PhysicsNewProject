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

    static {
        mp = new HashMap<>();
        mp.put(L.plus, "+");
        mp.put(L.minus, "-");
        mp.put(L.mult, "*");
        mp.put(L.div, "/");
        mp.put(L.power, "^");
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
