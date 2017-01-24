
import graphics.Painter;
import model.*;
import model.Point;
import parser.*;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Manager {
    // TODO: need to clear params
    private static String function; // to much params
    private static Node expression;
    private static PointsGetter pointsGetter;
    private static int count;
    private static InputReader in;
    static ArrayList<ArrayList<Point>> points;

    static Manager myManager = new Manager();

    private static class MyThread extends Thread {
        int index;

        MyThread(int index) {
            super();
            this.index = index;
        }

        public void run() {
            myManager.start(index);
        }
    }

    public static void main(String[] args) throws IOException {
        new Gui().run();
    }

    public void paintRays(String function) {
        try {
            in = new InputReader(new FileInputStream("input.txt"));
            makeExpression(function);
        } catch (IOException e) {
            e.printStackTrace();
        }
        points = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            points.add(new ArrayList<>());
            new MyThread(i).run();
        }
        new Painter().paintRay(points);
    }

    public void selfFocus() {
        double alphas[] = new double[]{2.1, 0.1, 0.1};
        ArrayList<ArrayList<Point>> d = new SelfFocusing().getGraphicCoordinates(alphas,
                (x, y) -> (x * y),
                x -> (1e-3 - Math.abs(5e-4 - x * 1e-4)),
                0.1, 0.5, 20, 20);
        new Painter().paintRay(d);
    }

    private synchronized void start(int index) {
        try {

            makePointsGetter();
/**
 *  get some data for constructor with arguments if we have them in input
 *  or
 *  make new pointsGetter with default values for all arguments
 */
            int iters = 50;
            for (int i = 0; i < iters; i++) {
                double curX = pointsGetter.getX();
                double curY = pointsGetter.getY();
                double nextN = expression.evaluate(curY); // TODO: fix bag with double value

                pointsGetter.getNextPoint(nextN);  // TODO: check this method
                pointsGetter.setCurN(nextN);
                points.get(index).add(new Point(curX, curY));
            }
            double xx = pointsGetter.getX();
            double yy = pointsGetter.getY();
            points.get(index).add(new Point(xx, yy));

        } catch (IOException e) {
            System.out.println("Need to change input");
        }
    }

    private static void makeExpression(String function) throws IOException {
        Parser parser = new Parser();
        count = in.nextInt();
        expression = parser.getNode(function);
    }

    private synchronized void makePointsGetter() throws IOException {
        double del = in.nextDouble();
        double curN = in.nextDouble();
        double curSin = Math.sin(Math.toRadians(in.nextDouble()));
        double x = in.nextDouble();
        double y = in.nextDouble();
        int part = in.nextInt();
        pointsGetter = new PointsGetter(del, curN, curSin, x, y, part);
    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

    }
}
