package Labs.Lab10;

import javax.swing.*;
import java.awt.*;

public class SwingTimerEx extends JFrame {

    public SwingTimerEx() {

        initUI();
    }

    private void initUI() {

        Factory factory = new Factory();
        add(new Map(factory.getCountry()));

        ImageIcon icon = new ImageIcon("./src/paint.png");
        setIconImage(icon.getImage());

        setResizable(false);
        pack();

        setTitle("Map");
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