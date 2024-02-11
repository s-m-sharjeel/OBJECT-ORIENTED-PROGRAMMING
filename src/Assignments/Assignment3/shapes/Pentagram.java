package Assignments.Assignment3.shapes;

import java.awt.*;
import java.awt.geom.Line2D;

public class Pentagram extends Shape {

    public Pentagram() {}

    public Pentagram(Color fillColor) {
        super(fillColor);
    }

    public Pentagram(Color fillColor, Color strokeColor) {
        super(fillColor, strokeColor);
    }

    public void draw(Graphics g) {

        int[] x = new int[10];
        int[] y = new int[10];

        int angle = 18;

        for (int i = 0; i < 10; i++) {


            int tempX;
            int tempY;

            if (i % 2 == 0) {

                tempX = point.x + size + (int) (size * Math.cos(Math.toRadians(angle)));
                tempY = point.y + size - (int) (size * Math.sin(Math.toRadians(angle)));

            } else {

                tempX = point.x + size + (int) ((size / 2) * Math.cos(Math.toRadians(angle)));
                tempY = point.y + size - (int) ((size / 2) * Math.sin(Math.toRadians(angle)));

            }

            angle += 36;

            x[i] = tempX;
            y[i] = tempY;

        }

        g.setColor(fillColor);
        g.fillPolygon(x, y, 10);

        if (stroke > 0) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(strokeColor);
            g2.setStroke(new BasicStroke(stroke));
            g2.draw(new Line2D.Float(x[0], y[0], x[1], y[1]));
            g2.draw(new Line2D.Float(x[1], y[1], x[2], y[2]));
            g2.draw(new Line2D.Float(x[2], y[2], x[3], y[3]));
            g2.draw(new Line2D.Float(x[3], y[3], x[4], y[4]));
            g2.draw(new Line2D.Float(x[4], y[4], x[5], y[5]));
            g2.draw(new Line2D.Float(x[5], y[5], x[6], y[6]));
            g2.draw(new Line2D.Float(x[6], y[6], x[7], y[7]));
            g2.draw(new Line2D.Float(x[7], y[7], x[8], y[8]));
            g2.draw(new Line2D.Float(x[8], y[8], x[9], y[9]));
            g2.draw(new Line2D.Float(x[9], y[9], x[0], y[0]));
            g2.setStroke(new BasicStroke(0));
        }

    }

    @Override
    public String toString() {
        return "Pentagram\n" + fillColor.getRed() + "," + fillColor.getGreen() + "," + fillColor.getBlue() + "\n" + strokeColor.getRed() + "," + strokeColor.getGreen() + "," + strokeColor.getBlue() + "\n" + stroke + "\n" + point.x + "," + point.y + "\n" + size + "\n";
    }
}
