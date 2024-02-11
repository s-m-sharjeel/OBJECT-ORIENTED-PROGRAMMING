package Assignments.Assignment3.shapes;

import java.awt.*;
import java.awt.geom.Line2D;

public class EquilateralTriangle extends Triangle {

    public EquilateralTriangle() {}

    public EquilateralTriangle(Color fillColor) {
        super(fillColor);
    }

    public EquilateralTriangle(Color fillColor, Color strokeColor) {
        super(fillColor, strokeColor);
    }

    @Override
    public void draw(Graphics g) {

        int[] x = new int[3];
        int[] y = new int[3];

        int angle = 90;

        for (int i = 0; i < 3; i++) {

            int tempX;
            int tempY;

            tempX = point.x + size + (int) (size * Math.cos(Math.toRadians(angle)));
            tempY = point.y + size - (int) (size * Math.sin(Math.toRadians(angle)));

            x[i] = tempX;
            y[i] = tempY;

            angle += 120;

        }

        g.setColor(fillColor);
        g.fillPolygon(x, y, 3);

        if (stroke > 0) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(strokeColor);
            g2.setStroke(new BasicStroke(stroke));
            g2.draw(new Line2D.Float(x[0], y[0], x[1], y[1]));
            g2.draw(new Line2D.Float(x[1], y[1], x[2], y[2]));
            g2.draw(new Line2D.Float(x[2], y[2], x[0], y[0]));
            g2.setStroke(new BasicStroke(0));
        }
    }

    @Override
    public String toString() {
        return "Equilateral Triangle\n" + fillColor.getRed() + "," + fillColor.getGreen() + "," + fillColor.getBlue() + "\n" + strokeColor.getRed() + "," + strokeColor.getGreen() + "," + strokeColor.getBlue() + "\n" + stroke + "\n" + point.x + "," + point.y + "\n" + size + "\n";
    }
}
