package Assignments.Assignment3.shapes;

import java.awt.*;
import java.awt.geom.Line2D;

public class Hexagon extends Shape {

    public Hexagon() {}

    public Hexagon(Color fillColor) {
        super(fillColor);
    }

    public Hexagon(Color fillColor, Color strokeColor) {
        super(fillColor, strokeColor);
    }

    @Override
    public void draw(Graphics g) {
        int[] x = new int[6];
        int[] y = new int[6];
        x[0] = point.x + width/2;
        y[0] = point.y + height;
        x[1] = point.x;
        y[1] = point.y + (int)(height*0.66);
        x[2] = point.x;
        y[2] = point.y + (int)(height*0.33);
        x[3] = point.x + width/2;
        y[3] = point.y;
        x[4] = point.x + width;
        y[4] = point.y + (int)(height*0.33);
        x[5] = point.x + width;
        y[5] = point.y + (int)(height*0.66);

        g.setColor(fillColor);
        g.fillPolygon(x, y, 6);

        if (stroke > 0) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(strokeColor);
            g2.setStroke(new BasicStroke(stroke));
            g2.draw(new Line2D.Float(x[0], y[0], x[1], y[1]));
            g2.draw(new Line2D.Float(x[1], y[1], x[2], y[2]));
            g2.draw(new Line2D.Float(x[2], y[2], x[3], y[3]));
            g2.draw(new Line2D.Float(x[3], y[3], x[4], y[4]));
            g2.draw(new Line2D.Float(x[4], y[4], x[5], y[5]));
            g2.draw(new Line2D.Float(x[5], y[5], x[0], y[0]));
            g2.setStroke(new BasicStroke(0));
        }
    }

    @Override
    public String toString() {
        return "Hexagon\n" + fillColor.getRed() + "," + fillColor.getGreen() + "," + fillColor.getBlue() + "\n" + strokeColor.getRed() + "," + strokeColor.getGreen() + "," + strokeColor.getBlue() + "\n" + stroke + "\n" + point.x + "," + point.y + "\n" + width + "\n" + height + "\n";
    }
}

