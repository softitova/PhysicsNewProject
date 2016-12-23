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
        System.out.println(curSin);
        curN = nextN;
    }

//    private double offset() {
//        return Math.atan(Math.asin(curSin));
//    }
    // здесь должен быть модуль - так как знак тебе не нужен и  ты его регулируешь сам
    // и еще надо умножить на  delta
    // ибо тангенст - это отношение x/delta  и вообще с какого фига тут арктангенс когда тут должен быть тангенс???

    private double offset() {
        double xx = Math.tan(Math.asin(curSin)) * DELTA;
        return xx >= 0 ? xx : -1 * xx;
    }

    /**
     * returns new point after passing the distance with new N
     */
    void getNextPoint(double nextN) {
        double prevSin = curSin;
        getNextSin(nextN);
        switch (part) {
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
            case 1:
                if (curSin > 1 || curSin < -1) { // исправила условия и добавила синус меньше -1
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
    }
}