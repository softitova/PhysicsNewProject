import java.util.ArrayList;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

import model.*;

/**
 * Created by Lenovo on 27.12.2016.
 */
public class SelfFocusing {
    // Environment characteristics

    final double INF = 1e+10;

    SelfFocusing() {
    }

    double getSinB(Point a, Point b, double n) {
        b = b.minus(a);
        double sinA = b.y / b.norm();
        return sinA / n;
    }

    Point rotateVector(Point a, Point b, double cos, double sin) {
        return  b.minus(a).rotate(cos, sin);
    }



    ArrayList<ArrayList<Point>> getGraphicCoordinates(double[] alphas, BinaryOperator<Double> elfield, UnaryOperator<Double> intensity, double deltaX, double deltaY, double w, double h) {
        assert alphas.length >= 3;
        double maximalIntensity = -INF, maxY = -INF;

        for (double y = 0; y < h; y += deltaY) {
            double cur = intensity.apply(y);
            if (cur > maximalIntensity) {
                maximalIntensity = cur;
                maxY = y;
            }
        }

        ArrayList<ArrayList<Point>> ans = new ArrayList<>();


        for (double y = 0; y < h; y += deltaY) {
            double intens = intensity.apply(y);
            ArrayList<Point> t = new ArrayList<>();
            t.add(new Point(0, y));
            t.add(new Point(1, y));
            for (double x = 1 + deltaX; x < w; x += deltaX) {
                double e = elfield.apply(x, y);
                double i = e * e;
                double alpha = alphas[0] + alphas[3] * i;
                double n = sqrt(1 + 4 * Math.PI * intens * alpha);

                int last = t.size();

                double sin = getSinB(t.get(last - 2), t.get(last - 1), n);
                double cos = sqrt(1 - sin * sin) * (y > maxY ? -1 : 1);
                Point p = t.get(last - 1).add(rotateVector(t.get(last - 2), t.get(last - 1), cos, sin));

                t.add(p);
            }

            ans.add(t);
        }
        return ans;
    }
}
