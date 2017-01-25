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

        System.out.println(b);
        double cosA = b.y / b.norm();
        double sinA = sqrt(1 - cosA * cosA);
        System.out.println("\t" + cosA + " " + sinA);
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
        ArrayList<Double> intens = new ArrayList<>();


        for (double y = 0; y < h; y += deltaY) {
            intens.add(intensity.apply(y));
            ArrayList<Point> t = new ArrayList<>();
            t.add(new Point(0, y));
            t.add(new Point(1, y));
            ans.add(t);
        }
        for (int iter = 1; iter < 30; iter++) {
            ArrayList<Point> directions = new ArrayList<>();
            for (int i = 0; i < intens.size(); i++) {
                Point last = ans.get(i).get(iter);
                double e = elfield.apply(last.x, last.y);
                double in = e * e;
                double alpha = alphas[0] + alphas[2] * in;
                double n = sqrt(1 + 4 * Math.PI * intens.get(i) * alpha);

//                n = (1) + (x / w);
                System.out.println(n);
                double cos = getSinB(ans.get(i).get(iter - 1), last, n);
                double sin = sqrt(1 - cos * cos);
                System.out.println(cos + " " + sin);
                Point p = rotateVector(new Point(0, 0), new Point(1, 0), cos, sin);

//                ans.get(i).add(p.add(last));
                directions.add(p.div(p.norm()));
            }
            for (int i = 0; i < intens.size(); ++i) {
                Point dir = new Point(0, 0);
                for (int j = 0; j < intens.size(); j++) {
                    Point otherDest = directions.get(j).add(ans.get(j).get(iter));
                    Point dif = otherDest.minus(ans.get(i).get(iter));
                    double k = 1 + ans.get(j).get(iter).minus(ans.get(i).get(iter)).norm();
                    dir = dir.add(dif.mult(intens.get(j) / dif.norm() / k));
                }
//                System.out.println(dir);
                ans.get(i).add(dir.div(dir.norm()).add(ans.get(i).get(iter)));
            }
        }
//        System.out.println(ans);
        return ans;
    }

    double f(double a, double b) {
        if (a == b)
            return 0;
        return a > b ? -1 : 1;
    }
}
