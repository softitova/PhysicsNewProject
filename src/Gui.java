import com.sun.javafx.event.CompositeEventTarget;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by vadim on 28.12.16.
 */
public class Gui {

    void run() {
        javax.swing.SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private void fillComponents(JFrame frame) {
        Container panel = frame.getContentPane();

        JLabel functionLabel = new JLabel("Function");
        functionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(functionLabel);
        JTextField functionText = new JTextField();
        panel.add(functionText);


        JButton startButton = new JButton("Draw");
        panel.add(startButton);

        JButton MekhrubonButton = new JButton("Self focusing");
        panel.add(MekhrubonButton);

        Manager manager = new Manager();

        startButton.addActionListener(e -> manager.paintRays(functionText.getText()));

        MekhrubonButton.addActionListener(e -> manager.selfFocus());

        panel.setLayout(new GridLayout(2, 2));
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Начальные данные");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        fillComponents(frame);

        frame.pack();
        frame.setVisible(true);
    }
}
