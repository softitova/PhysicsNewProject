/**
 * Created by Sofia on 20.11.16.
 */

public class PointsGetter {
    /**
     * defult values whith parametrs ehich we can change
     */

    private double DELTA = 1;
    private double curN = 1;
    private double curSin = 1/2;
    private double nextX = 1;
    private double nextY = 1;

    /**
     * default ctor
     */
    PointsGetter() {}

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
        getNextSin(nextN);
        nextX += 1 / (Math.tan(Math.asin(curSin)) / DELTA);
        nextY += DELTA;
    }

}