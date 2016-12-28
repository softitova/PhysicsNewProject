import javafx.util.Pair;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * Created by Sofia on 20.11.16.
 */

public class PointsGetter {
    /**
     * default values with params which we can change
     */

    private double DELTA = 1;
    private double curN = 1;
    private double curSin = 1 / 2;
    private double x = 1;
    private double y = 1;
    private int part = 3;


    /**
     * default ctor
     */
    PointsGetter() {
    }

    public double getCurSin() {
        return curSin;
    }

    /**
     * ctor with args
     */
    PointsGetter(double DELTA, double curN, double curSin, double x, double y, int part) {
        this.DELTA = DELTA;
        this.curN = curN;
        this.curSin = curSin;
        this.x = x;
        this.y = y;
        this.part = part;
    }

    double getDELTA() {
        return DELTA;
    }

    double getX() {
        return x;
    }

    public void setCurN(double curN) {
        this.curN = curN;
    }

    double getY() {
        return y;
    }

    /**
     * set current sin and currwnt N
     */
    private void getNextSin(double nextN) {
        curSin = curSin * curN / nextN;
        curN = nextN;
    }

    private double offset() {
        return Math.tan(Math.asin(curSin)) * DELTA;
    }

    /**
     * returns new point after passing the distance with new N
     */

    Pair<Integer, Double[]> MekhrubonTriesToOptimizeThis88888Code(int part, double curSin, double prevSin, double x, double y) {
        ArrayList<Predicate<Double>> check = new ArrayList<>();
        check.add(v -> (v > 1 || v < -1));
        check.add(v -> v >= 0);
        check.add(v -> true);
        ArrayList<UnaryOperator<Integer>> newParts = new ArrayList<>();
        newParts.add(q -> (5 - q));
        newParts.add(q -> q);
        newParts.add(q -> q % 4 + 1);
        double mX[][] = new double[][]{{-1, 1, 1, -1}, {-1, 1, 1, -1}, {1, -1, -1, 1}};
        double mY[][] = new double[][]{{1, 1, -1, -1}, {-1, -1, 1, 1}, {-1, -1, 1, 1}};
        for (int i = 0; i < 3; ++i) {
            if (check.get(i).test(curSin)) {
                if (i == 0)
                    curSin = prevSin;
                if (i == 2)
                    curSin = -curSin;

                x += mX[i][part - 1] * offset();
                y += mY[i][part - 1] * DELTA;
                part = newParts.get(i).apply(part);
                break;
            }
        }
        return new Pair<>(part, new Double[]{x, y, curSin});
    }

    void getNextPoint(double nextN) {
        double prevSin = curSin;
        getNextSin(nextN);

        Pair<Integer, Double[]> res = MekhrubonTriesToOptimizeThis88888Code(part, curSin, prevSin, x, y);

        switch (part) {
            case 1:
                if (curSin > 1 || curSin < -1) {
                    curSin = prevSin;
                    x -= offset();
                    y += DELTA;
                    part = 4;
                } else if (curSin >= 0) {
                    x -= offset();
                    y -= DELTA;
                    part = 1;
                } else {
                    curSin = -curSin;
                    x += offset();
                    y -= DELTA;
                    part = 2;
                }
                break;
            case 2:
                if (curSin > 1 || curSin < -1) {
                    curSin = prevSin;
                    y += DELTA;
                    x += offset();
                    part = 3;
                } else if (curSin >= 0) {
                    x += offset();
                    y -= DELTA;
                    part = 2;
                } else {
                    curSin = -curSin;
                    x -= offset();
                    y -= DELTA;
                    part = 1;
                }
                break;
            case 3:
                if (curSin > 1 || curSin < -1) {
                    curSin = prevSin;
                    x += offset();
                    y -= DELTA;
                    part = 2;
                } else if (curSin >= 0) {
                    x += offset();
                    y += DELTA;
                    part = 3;
                } else {
                    curSin = -curSin;
                    x -= offset();
                    y += DELTA;
                    part = 4;
                }
                break;
            default:
                assert part == 4;
                if (curSin > 1 || curSin < -1) {
                    curSin = prevSin;
                    x -= offset();
                    y -= DELTA;
                    part = 1;
                } else if (curSin >= 0) {
                    x -= offset();
                    y += DELTA;
                    part = 4;
                } else {
                    curSin = -curSin;
                    x += offset();
                    y += DELTA;
                    part = 3;
                }
        }
        assert part == res.getKey();
        assert x == res.getValue()[0];
        assert y == res.getValue()[1];
        assert curSin == res.getValue()[2];
    }
}