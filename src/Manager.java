import graphics.Painter;
import model.Node;
import parser.Parser;
import model.Point;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Manager {
    // TODO: need to clear params
    private static String function; // to much params
    private static Node expression;
    private static PointsGetter pointsGetter;
    private static int count;
    private static StringTokenizer st;
    private static BufferedReader in;


    private static void run() {
        //System.out.println("runnable thread - " + Thread.currentThread().getName());
        new Manager().start(); // TODO how to read in concurrency
    }

    public static void main(String[] args) throws IOException {
        in = new BufferedReader(new FileReader("input.txt")); // TODO :  change available to user fields
        PrintWriter out = new PrintWriter(new File("output.txt"));
        makeExpression(in);

        /**
         * count is amount of light lines in input file
         */
        //count = Integer.parseInt(in.readLine()) ; TODO fix input file
        count = 3; //
        for (int i = 0; i < count ; i++) {
            new Thread(Manager::run).start();
        }

        out.close();
    }

    private void start() {
        try {
            double height = 800;
            makePointsGetter(st);
            System.out.println("here");
/**
 *  get some data for constructor with arguments if we have them in input
 *  or
 *  make new pointsGetter with default values for all arguments
 */
//        if (st.countTokens() == 5) {   // TODO: normal constructor with correct fields of users data
//            pointsGetter = new PointsGetter(
//                    Double.parseDouble(st.nextToken()),
//                    Double.parseDouble(st.nextToken()),
//                    Math.sin(Math.toRadians(Double.parseDouble(st.nextToken()))),
//                    Double.parseDouble(st.nextToken()),
//                    Double.parseDouble(st.nextToken()));
//        } else {
//            pointsGetter = new PointsGetter();
//        }
            // pointsGetter = new PointsGetter(30, 1, 0.85, 400, 0);

            height /= pointsGetter.getDELTA();

            List<Point> points = new ArrayList<>();
            for (int i = 0; i < height; i++) {
                double curX = pointsGetter.getNextX();
                double curY = pointsGetter.getNextY();
                double nextN = expression.evaluate(curX, curY); // TODO: fix bag with double value
                pointsGetter.getNextPoint(nextN);  // TODO: check this method
                pointsGetter.setCurN(nextN);
                points.add(new Point((int) curX, (int) curY));
                System.out.println();
            }

            points.add(new Point((int) pointsGetter.getNextX(), (int) pointsGetter.getNextY()));

            new Painter().paintRay(points);
        } catch (IOException e) {
            System.out.println("Need to change input");
        }
    }

    private static void makeExpression(BufferedReader in) throws IOException {
        Parser parser = new Parser();
        function = "2";  // TODO: move to input file
        expression = parser.getNode(function);
    }

    private synchronized void makePointsGetter(StringTokenizer st) throws IOException {
        st = new StringTokenizer(in.readLine());
        pointsGetter = new PointsGetter(
                Double.parseDouble(st.nextToken()),
                Double.parseDouble(st.nextToken()),
                Math.sin(Math.toRadians(Double.parseDouble(st.nextToken()))),
                Double.parseDouble(st.nextToken()),
                Double.parseDouble(st.nextToken()));

    }
}
