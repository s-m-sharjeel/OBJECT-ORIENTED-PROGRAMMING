package Assignments.Assignment3.drawing;

import Assignments.Assignment3.shapes.Shape;

public class ActiveButton extends Button{

    public ActiveButton(Shape shape) {
        super(shape);
    }

    public ActiveButton(String text) {
        super(text);
    }

    public ActiveButton(String text, int x, int y, int width, int height) {
        super(text, x, y, width, height);
    }

    public void isClicked(int x, int y) {

        // pressed if clicked within the bounds of the button
        pressed = x > this.x && x < this.x + width && y > this.y && y < this.y + height;

    }


}
