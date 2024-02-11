package Assignments.Assignment3.drawing;

import java.awt.*;
import java.util.LinkedList;

import Assignments.Assignment3.shapes.*;
import Assignments.Assignment3.shapes.Rectangle;
import Assignments.Assignment3.shapes.Shape;

public abstract class Button extends Rectangle {

    private Shape shape;
    private String text = "";
    protected LinkedList<Button> buttons;
    protected boolean pressed;
    private boolean visible;
    private boolean leftAlign;
    private int textHeight = 0;

    public Button(String text) {
        this.text = text;
    }

    public Button(Shape shape) {
        this.shape = shape;
    }

    public Button(String text, int x, int y, int width, int height){
        super(x, y, width, height);
        this.text = text;
    }

    public Button(String text, LinkedList<Button> buttons) {
        this.text = text;
        this.buttons = buttons;
    }

    /**
     * paints a button w.r.t its state
     */
    public void paint(Graphics g){

        // sets the state of the 3D rectangle
        setRaised(!pressed);

        // draws a 3D rectangle
        super.draw(g);

        // draws the icon
        drawText(g);
        drawShape(g);

    }

    /**
     * an abstract function which sets the state of the button after a particular type of button is clicked
     * @param x is the x coordinate of the mouse
     * @param y is the y coordinate of the mouse
     */
    public abstract void isClicked(int x, int y);

    public Boolean isPressed() {return pressed;}

    /**
     * returns the icon shape of the button
     */
    public Shape getShape() {return shape;}

    public void setPressed(boolean pressed) {this.pressed = pressed;}

    /**
     * aligns the text to the left of the button
     */
    public void LEFT_ALIGN(){leftAlign = true;}

    public void setBounds(int x, int y, int width, int height) {
        point.x = x;
        point.y = y;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void setText(String text) {this.text = text;}

    public String getText() {return text;}

    public void setVisible(boolean visible) {this.visible = visible;}

    public boolean isVisible() {return visible;}

    /**
     * writes the text for the button icon
     */
    private void drawText(Graphics g){

        if(text.length() > 0) {

            g.setColor(Color.black);
            if (shape != null) {
                textHeight = 20;
                g.setColor(Color.lightGray);
                g.drawLine(x + 10, y + height - textHeight, x + width - 10, y + height - textHeight);

            } else textHeight = height;

            g.setColor(Color.black);
            g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, textHeight / 2));
            FontMetrics m = g.getFontMetrics();
            int stringWidth = m.stringWidth(text);
            int stringHeight = m.getAscent() - m.getDescent();

            if (leftAlign)
                g.drawString(text, x + stringHeight / 2, y + height - textHeight / 2 + stringHeight / 2);
            else g.drawString(text, x + width / 2 - stringWidth / 2, y + height - textHeight / 2 + stringHeight / 2);

        }

    }

    /**
     * draws the shape for the button icon
     */
    private void drawShape(Graphics g){

        if(shape != null){
            shape.setStrokeColor(Color.black);
            shape.setWidth(width/2 + width/4);
            shape.setHeight(height/2 + height/4 - textHeight);
            shape.setSize(Math.min(width, height - textHeight)/4 + Math.min(width, height - textHeight)/8);
            if (shape instanceof Circle || shape instanceof EquilateralTriangle || shape instanceof Pentagram)
                shape.setPoint(new Point(x + width/2 - shape.getSize(), y + height/2 - textHeight/2 - shape.getSize()));
            else shape.setPoint(new Point(x + width/8, y + height/8));

            shape.addPixel(x + width/4, y + height/4);
            shape.addPixel(x + width - width/4, y + height - textHeight - height/4);

            shape.draw(g);
        }

    }

}

