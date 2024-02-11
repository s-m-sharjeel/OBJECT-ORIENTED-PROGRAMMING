package Assignments.Assignment2.drawing;
import java.awt.*;

public class Rectangle extends Shape {

    Rectangle(Color color) {
        super(color);
    }

    @Override
    void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(point.x, point.y, width, length);
    }

    /**
     * @return the state of the Rectangle
     */
    @Override
    public String toString() {
        return "Rectangle\n" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + "\n" + point.x + "," + point.y + "\n" + width + "\n" + length + "\n";
    }
}
