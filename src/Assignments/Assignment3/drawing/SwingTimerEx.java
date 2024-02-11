package Assignments.Assignment3.drawing;

import javax.swing.*;
import java.awt.*;

public class SwingTimerEx extends JFrame {

    public SwingTimerEx() {

        initUI();
    }

    private void initUI() {

        add(new Panel());

        ImageIcon icon = new ImageIcon("./src/Assignments/Assignment3/paint.png");
        setIconImage(icon.getImage());

        setResizable(false);
        pack();
        setExtendedState(MAXIMIZED_BOTH);

        setTitle("Paint");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            SwingTimerEx ex = new SwingTimerEx();
            ex.setVisible(true);
        });
    }
}