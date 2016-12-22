/**
 * Created by Sofia on 20.11.16.
 */

public class PointsGetter {
    /**
     * defult values whith parametrs ehich we can change
     */

    private double DELTA = 1;
    private double curN = 1;
    private double curSin = 1 / 2;
    private double nextX = 1;
    private double nextY = 1;
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
    PointsGetter(double DELTA, double curN, double curSin, double nextX, double nextY) {
        this.DELTA = DELTA;
        this.curN = curN;
        this.curSin = curSin;
        this.nextX = nextX;
        this.nextY = nextY;
    }

    double getDELTA() {
        return DELTA;
    }

    double getNextX() {
        return nextX;
    }

    public void setCurN(double curN) {
        this.curN = curN;
    }

    double getNextY() {
        return nextY;
    }

    /**
     * set current sin and currwnt N
     */
    private void getNextSin(double nextN) {
        curSin = curSin * curN / nextN;
        curN = nextN;
    }

    /**
     * returns new point after passing the distance with new N
     */
    void getNextPoint(double nextN) {
        double prevSin = curSin;
        getNextSin(nextN);
        if (curSin > 1 || curSin < -1) {
            curSin = prevSin;
        }
//        System.out.println(Math.toDegrees(curSin) + " curSin");
//        System.out.println(Math.tan(Math.asin(curSin)) + " arctg");
        double x = (DELTA * (Math.tan(Math.asin(curSin))));
//        System.out.println(x + " first X");
        x *= x > 0 ? 1 : -1;
//        System.out.println(DELTA * (Math.tan(Math.asin(curSin))) + " DELTA * (Math.tan(Math.asin(curSin)))");
//        System.out.println(x + " x ");
        switch (part) {
            case 2:
                if (curSin > 1) {
                    nextX += x;
                    nextY += DELTA;
                    part = 3;
                } else if (curSin > 0) {
                    nextX += x;
                    nextY -= DELTA;
                    part = 2;
                } else {
                    nextX -= x;
                    nextY -= DELTA;
                    part = 1;
                }
                break;
            case 1:
                if (curSin > 1) {
                    nextX -= x;
                    nextY += DELTA;
                    part = 4;
                } else if (curSin > 0) {
                    nextX -= x;
                    nextY -= DELTA;
                    part = 1;
                } else {
                    nextX += x;
                    nextY -= DELTA;
                    part = 2;
                }
                break;
            case 3:
                if (curSin > 1) {
                    nextX += x;
                    nextY -= DELTA;
                    part = 2;
                } else if (curSin > 0) {
                    nextX += x;
                    nextY += DELTA;
                    part = 3;
//                    System.out.println(" here + " + nextX + " nextx, " + nextY + " nextY, ");
                } else {
                    nextX -= x;
                    nextY += DELTA;
                    part = 4;
                }
                break;
            default:
                if (curSin > 1) {
                    nextX -= x;
                    nextY -= DELTA;
                    part = 1;
                } else if (curSin > 0) {
                    nextX -= x;
                    nextY += DELTA;
                    part = 4;
                } else {
                    nextX += x;
                    nextY += DELTA;
                    part = 3;
                }
//                    nextX += 1 / (Math.tan(Math.asin(curSin)) / DELTA);
//                nextY += DELTA;
        }
    }
}