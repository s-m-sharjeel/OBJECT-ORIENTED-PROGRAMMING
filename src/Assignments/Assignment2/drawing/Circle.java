package Assignments.Assignment2.drawing;
import java.awt.*;

public class Circle extends Shape {

    Circle(Color color){
        super(color);
    }

    void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(point.x, point.y, size*2, size*2);
    }

    /**
     * @return the state of the Circle
     */
    public String toString(){
        return "Circle\n" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + "\n" + point.x + "," + point.y + "\n" + size + "\n";
    }

}
