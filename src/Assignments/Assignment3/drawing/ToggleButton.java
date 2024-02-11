package Assignments.Assignment3.drawing;

import java.util.LinkedList;
import Assignments.Assignment3.shapes.Shape;

public class ToggleButton extends Button{

    public ToggleButton(Shape shape) {
        super(shape);
    }

    public ToggleButton(String text) {
        super(text);
    }

    public ToggleButton(String text, LinkedList<Button> buttons) {
        super(text, buttons);
    }

    @Override
    public void isClicked(int x, int y) {

        // if within the bounds of the button
        if(x > this.x && x < this.x + width && y > this.y && y < this.y + height)
            pressed = !pressed;

    }

}
