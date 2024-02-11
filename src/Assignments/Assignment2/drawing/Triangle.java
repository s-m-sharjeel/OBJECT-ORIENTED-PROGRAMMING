package Assignments.Assignment2.drawing;
import java.awt.*;

public class Triangle extends Shape {

    Triangle(Color color) {
        super(color);
    }

    @Override
    void draw(Graphics g) {
        g.setColor(color);
        g.fillPolygon(x, y, 3);
    }

    /**
     * @return the state of the Triangle
     */
    @Override
    public String toString() {
        return "Triangle\n" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + "\n" + x[0] + "," + y[0] + "\n" + x[1] + "," + y[1] + "\n" + x[2] + "," + y[2] + "\n";
    }
}
