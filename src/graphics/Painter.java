package graphics;

import java.util.ArrayList;
import java.util.List;
import model.Point;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

/**
 * Created by vadim on 28.11.16.
 */

public class Painter {

    public void paintRay(ArrayList<ArrayList<Point>> points) {
        if (points == null) {
            return;
        }
        XYChart chart = QuickChart.getChart("Graphico for waves", "X", "Y", "0",
                getX(points.get(0)), getY(points.get(0)));
        SwingWrapper rapper = new SwingWrapper(chart);

        for (int i = 1; i < points.size(); ++i) {
            chart.addSeries(Integer.toString(i), getX(points.get(i)), getY(points.get(i)));
        }
        rapper.displayChart();
    }

    double[] getX(List<Point> list) {
        double[] x = new double[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            x[i] = list.get(i).x;
        }
        return x;
    }

    double[] getY(List<Point> list) {
        double[] y = new double[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            y[i] = list.get(i).y;
        }
        return y;
    }

}
