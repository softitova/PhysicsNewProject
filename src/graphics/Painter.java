package graphics;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import model.Point;

/**
 * Created by vadim on 28.11.16.
 */

public class Painter {

    public void paintRay(ArrayList<ArrayList<Point>> points) {
        JFrame jFrame = new JFrame();

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(new MyPanel(points));
        jFrame.pack();
        jFrame.setVisible(true);
    }

    class MyPanel extends JPanel {
        ArrayList<ArrayList<Point>> points = null;

        MyPanel(ArrayList<ArrayList<Point>> points) {
            super();
            this.points = points;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLUE);
//            System.out.println(points.size()+ " points size");
            System.out.println("MyPanel.paintComponent");
//            System.out.println(points);
            points.forEach(System.out::println);
            for (int i = 0; i < points.size(); ++i) {
//            int i =0;
                List<Point> cur = points.get(i);
                for (int j = 0; j < cur.size() - 1; ++j) {
                    Point prev = cur.get(j);
                    Point next = cur.get(j + 1);
//                    System.out.println(prev.x + " prev x  " +  prev.y + " prev y " ) ;
                    g.drawLine(prev.x, prev.y, next.x, next.y);
                }
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(800, 800);
        }
    }
}
