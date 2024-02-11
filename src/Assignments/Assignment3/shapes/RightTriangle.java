package Assignments.Assignment3.shapes;

import java.awt.*;
import java.awt.geom.Line2D;

public class RightTriangle extends Triangle {

    public RightTriangle() {}

    public RightTriangle(Color fillColor) {
        super(fillColor);
    }

    public RightTriangle(Color fillColor, Color strokeColor) {
        super(fillColor, strokeColor);
    }

    @Override
    public void draw(Graphics g) {
        int[] x = new int[3];
        int[] y = new int[3];
        x[0] = point.x;
        y[0] = point.y + height;
        x[1] = point.x;
        y[1] = point.y;
        x[2] = point.x + width;
        y[2] = point.y + height;
        g.setColor(fillColor);
        g.fillPolygon(x, y, 3);

        if (stroke > 0) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(strokeColor);
            g2.setStroke(new BasicStroke(stroke));
            g2.draw(new Line2D.Double(x[0], y[0], x[1], y[1]));
            g2.draw(new Line2D.Double(x[1], y[1], x[2], y[2]));
            g2.draw(new Line2D.Double(x[2], y[2], x[0], y[0]));
            g2.setStroke(new BasicStroke(0));
        }
    }

    @Override
    public String toString() {
        return "Right Triangle\n" + fillColor.getRed() + "," + fillColor.getGreen() + "," + fillColor.getBlue() + "\n" + strokeColor.getRed() + "," + strokeColor.getGreen() + "," + strokeColor.getBlue() + "\n" + stroke + "\n" + point.x + "," + point.y + "\n" + width + "\n" + height + "\n";
    }
}
