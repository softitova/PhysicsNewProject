
import graphics.*;
import model.*;
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
            System.out.println(index);
        }
    }

    public static void main(String[] args) throws IOException {
        in = new InputReader(new FileInputStream("input.txt"));
        PrintWriter out = new PrintWriter(new File("output.txt"));
        makeExpression();

        /**
         * count is amount of light lines in input file
         */
        //count = Integer.parseInt(in.readLine()) ; TODO fix input file
        //
        points = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            System.out.println(i);
            points.add(new ArrayList<>());
            new MyThread(i).run();
        }
        // System.out.println("Manager.main");
        new Painter().paintRay(points);

        out.close();
    }

    private synchronized void start(int index) {
        try {
            double height = 50;
            makePointsGetter();
/**
 *  get some data for constructor with arguments if we have them in input
 *  or
 *  make new pointsGetter with default values for all arguments
 */

            int iters = (int) (height / pointsGetter.getDELTA());
            for (int i = 0; i < iters; i++) {
                double curX = pointsGetter.getX();
                double curY = pointsGetter.getY();
                double nextN = expression.evaluate(curX, curY); // TODO: fix bag with double value
                System.out.println(nextN);
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

    private static void makeExpression() throws IOException {
        Parser parser = new Parser();
        function = in.next();
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
