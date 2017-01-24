package graphics;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import model.Point;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;



public class Painter {

    private XYChart chart = null;
    private SwingWrapper rapper = null;

    public void paintRay(ArrayList<ArrayList<Point>> points) {
        if (points == null) {
            return;
        }
        ArrayList<ArrayList<Point>> past = new ArrayList<>();
        for (int i = 0; i < points.size(); ++i) {
            past.add(new ArrayList<>());
        }

        chart = new XYChart(800, 800);
        rapper = new SwingWrapper(chart);

        TimerTask currentTask = new TimerTask() {
            @Override
            public boolean cancel() {
                return super.cancel();
            }

            @Override
            public long scheduledExecutionTime() {
                return super.scheduledExecutionTime();
            }

            @Override
            public void run() {
                boolean first = false;
                if (past.get(0).isEmpty()) {
                    first = true;
                }
                for (int i = 0; i < points.size(); ++i) {
                    if (past.get(i).size() < points.get(i).size()) {
                        past.get(i).add(points.get(i).get(past.get(i).size()));
                        if (!first)
                            chart.updateXYSeries(Integer.toString(i), getX(past.get(i)), getY(past.get(i)), null);
                        else
                            chart.addSeries(Integer.toString(i), getX(past.get(i)), getY(past.get(i)));
                    }
                }
                if (first) {
                    rapper.displayChart();
                } else {
                    rapper.repaintChart();
                }
            }
        };

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(currentTask, 0, 200);
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
