package Assignments.Assignment2.manager;

import Assignments.Assignment2.drawing.Panel;
import java.awt.*;
import javax.swing.*;

class DrawingManager {

    public static void main(String[] args) {

        String filePath = "./src/Assignments/Assignment2/shapes.txt";

        // creates frame
        JFrame frame = new JFrame("Shapes");
        // creates panel
        Panel panel = new Panel(filePath);
        // adds panel to frame
        frame.add(panel);

        // displays maximised frame
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // stores all the shapes in a text file
        panel.storeShapes();

    }

}
