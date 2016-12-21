package parser;

import model.*;
/**
 * Created by vadim on 26.11.16.
 */
public class Parser {

    private L lex;
    private int pos;
    private String s;
    private double value;
    private String var;

    private void getLex() {
        if (pos >= s.length()) {
            lex = L.end;
            return;
        }

        switch (s.charAt(pos)) {
            case '+':
                lex = L.plus;
                break;
            case '-':
                lex = L.minus;
                break;
            case '*':
                lex = L.mult;
                break;
            case '/':
                lex = L.div;
                break;
            case '^':
                lex = L.power;
                break;
            case '(':
                lex = L.open;
                break;
            case ')':
                lex = L.close;
                break;
            case 'x':
            case 'y':
                lex = L.var;
                var = String.valueOf(s.charAt(pos));
                break;
            default:
                verify(Character.isDigit(s.charAt(pos)));
                lex = L.val;
                value = 0;
                double muler = 1;
                boolean dot = false;
                while (pos < s.length() && (Character.isDigit(s.charAt(pos)) || s.charAt(pos) == '.')) {
                    if (s.charAt(pos) == '.') {
                        verify(!dot);
                        dot = true;
                    } else {
                        if (dot) {
                            muler /= 10.0;
                            value += muler * (s.charAt(pos) - '0');
                        } else {
                            value *= 10.0;
                            value += s.charAt(pos) - '0';
                        }
                    }
                    pos++;
                }
                pos--;
        }
        pos++;
    }

    private Node expression() {
        Node ret = summand();
        while (lex.equals(L.plus) || lex.equals(L.minus)) {
            L cur = lex;
            getLex();
            ret = new Node(ret, summand(), cur);
        }
        return ret;
    }

    private Node summand() {
        Node ret = multiplier();
        while (lex.equals(L.mult) || lex.equals(L.div)) {
            L cur = lex;
            getLex();
            ret = new Node(ret, multiplier(), cur);
        }
        return ret;
    }

    private void verify(boolean x) {
        if (!x) {
            throw new ArithmeticException("invalid");
        }
    }

    private Node multiplier() {
        Node ret = alone();
        if (lex.equals(L.power)) {
            getLex();
            ret = new Node(ret, expression(), L.power);
        }
        return ret;
    }

    private Node alone() {
        Node ret = null;
        switch (lex) {
            case open:
                getLex();
                ret = expression();
                verify(lex == L.close);
                getLex();
                return ret;
            case var:
                ret = new Variable(var);
                getLex();
                return ret;
            case val:
                ret = new Constant(value);
                getLex();
                return ret;
            default:
                verify(false);
                return null;
        }
    }

    public Node getNode(String expr) {
        pos = 0;
        s = expr;
        getLex();
        Node ret = expression();
        verify(lex == L.end);
        return ret;
    }
}
