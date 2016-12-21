package graphics;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import model.Point;

/**
 * Created by vadim on 28.11.16.
 */

public class Painter {

    public void paintRay(List<List<Point>> points) {
        JFrame jFrame = new JFrame();

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(new MyPanel(points));
        jFrame.pack();
        jFrame.setVisible(true);
    }

    class MyPanel extends JPanel {
        List<List<Point>> points = null;

        MyPanel(List<List<Point>> points) {
            super();
            this.points = points;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLUE);
            for (int i = 0; i < points.size(); ++i) {
                List<Point> cur = points.get(i);
                for (int j = 0; j < cur.size() - 1; ++j) {
                    Point prev = cur.get(i);
                    Point next = cur.get(i + 1);
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
