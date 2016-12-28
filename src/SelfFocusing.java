import model.Point;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

import static java.lang.Math.min;
import static java.lang.Math.sqrt;

public class SelfFocusing {
    final double INF = 1e+10;
    PrintWriter out;

    SelfFocusing() {
    }

    double getSinB(Point a, Point b, double n) {
        b = b.minus(a);

        double cosA = b.y / b.norm();
        double sinA = sqrt(1 - cosA * cosA);
//        out.println("Sin Alpha = " + sinA);
        return sinA / n;
    }

    Point rotateVector(Point a, Point b, double cos, double sin) {
        return b.minus(a).rotate(cos, sin);
    }


    ArrayList<ArrayList<Point>> getGraphicCoordinates(double[] alphas, BinaryOperator<Double> elfield, UnaryOperator<Double> intensity, double deltaX, double deltaY, double w, double h) {
//        try {
//            out = new PrintWriter(new File("Output.txt"));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        assert alphas.length >= 3;
        double maximalIntensity = -INF, maxY = -INF;

        for (double y = 0; y < h; y += deltaY) {
            double cur = intensity.apply(y);
            System.out.println(cur);
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
            t.add(new Point(1, y - (y - maxY) * 1e-3));
            for (double x = 1 + deltaX; x < w; x += deltaX) {
                double e = elfield.apply(x, y);
                double i = e * e;
                double alpha = alphas[0] + alphas[2] * i;
                double n = sqrt(1 + 4 * Math.PI * intens * alpha);

//                n = (1) + (x / w);
                int last = t.size();

                double cos = min(getSinB(t.get(last - 2), t.get(last - 1), n), 1.);
                double sin = sqrt(1 - cos * cos);
                Point p = rotateVector(new Point(0, 0), new Point(1, 0), cos, sin * f(y, maxY));

                p = p.mult(1).add(t.get(last - 1));
                t.add(p);
            }

            ans.add(t);
        }
        return ans;
    }

    double f(double a, double b) {
        if (a == b)
            return 0;
        return a > b ? -1 : 1;
    }
}
