import com.sun.deploy.uitoolkit.impl.awt.AWTErrorPanel;
import com.sun.xml.internal.bind.v2.TODO;

import graphics.*;
import model.*;
import parser.*;

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
        in = new BufferedReader(new FileReader("input.txt")); // TODO :  change available to user fields
        PrintWriter out = new PrintWriter(new File("output.txt"));
        makeExpression(in);

        /**
         * count is amount of light lines in input file
         */
        //count = Integer.parseInt(in.readLine()) ; TODO fix input file
        count = 3; //
        points = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            points.add(new ArrayList<>());
            new MyThread(i).run();
        }
        System.out.println("Manager.main");
        new Painter().paintRay(points);

        out.close();
    }

    private void start(int index) {
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
            System.out.println(height + " height");


            for (int i = 0; i < 5; i++) { // TODO change 5 to height
                getNewPoint(i);
                System.out.println();
            }
            int xx = (int) pointsGetter.getNextX();
            int yy = (int) pointsGetter.getNextY();
//            System.out.println(xx + " = xx " + yy + " = yy "  + index + " index ");
            points.get(index).add(new Point(xx, yy));

        } catch (IOException e) {
            System.out.println("Need to change input");
        }
    }

    private static synchronized void getNewPoint(int index) {
        double curX = pointsGetter.getNextX();
        double curY = pointsGetter.getNextY();
        double nextN = expression.evaluate(curX, curY); // TODO: fix bag with double value
//                System.out.println(curX + " curX " + curY + " curY " + nextN + " nextN " );
        pointsGetter.getNextPoint(nextN);  // TODO: check this method
        pointsGetter.setCurN(nextN);
        points.get(index).add(new Point((int) curX, (int) curY));
    }

    private static void makeExpression(BufferedReader in) throws IOException {
        Parser parser = new Parser();
        function = "2";  // TODO: move to input file
        expression = parser.getNode(function);
    }

    private synchronized void makePointsGetter(StringTokenizer st) throws IOException {
        st = new StringTokenizer(in.readLine());
        double del = Double.parseDouble(st.nextToken());
        double curN = Double.parseDouble(st.nextToken());
        double curSin = Math.sin(Math.toRadians(Double.parseDouble(st.nextToken())));
        double x = Double.parseDouble(st.nextToken());
        double y = Double.parseDouble(st.nextToken());
        System.out.println(del + " " + curN + " " + curSin + " " + x + " " + y + " ");
        pointsGetter = new PointsGetter(del, curN, curSin, x, y);

//        pointsGetter = new PointsGetter(
//                Double.parseDouble(st.nextToken()),
//                Double.parseDouble(st.nextToken()),
//                Math.sin(Math.toRadians(Double.parseDouble(st.nextToken()))),
//                Double.parseDouble(st.nextToken()),
//                Double.parseDouble(st.nextToken()));

    }
}
