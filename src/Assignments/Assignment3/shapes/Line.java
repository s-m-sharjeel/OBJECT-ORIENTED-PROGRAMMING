package Assignments.Assignment3.shapes;

import java.awt.*;
import java.awt.geom.Line2D;

public class Line extends Shape {

    public Line(Color fillColor) {
        super(fillColor);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(fillColor);
        for (Circle pixel : linePixels) {
            g.fillOval(pixel.point.x, pixel.point.y, stroke, stroke);
        }

        // connects the pixels by a line
        if (linePixels.size() > 1 && stroke > 0 && stroke < 7) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(fillColor);
            g2.setStroke(new BasicStroke(stroke));
            for (int i = 0; i < linePixels.size() - 1; i++) {
                g2.draw(new Line2D.Double(linePixels.get(i).point.x + stroke/2, linePixels.get(i).point.y + stroke/2, linePixels.get(i + 1).point.x + stroke/2, linePixels.get(i + 1).point.y + stroke/2));
            }
            g2.setStroke(new BasicStroke(0));
        }



    }

    @Override
    public String toString() {
        String s = "Line\n" + fillColor.getRed() + "," + fillColor.getGreen() + "," + fillColor.getBlue() + "\n" + stroke + "\n";
        for (Circle pixel: linePixels) {
            s += pixel.point.x + "," + pixel.point.y + "\n";
        }
        return s;
    }
}
