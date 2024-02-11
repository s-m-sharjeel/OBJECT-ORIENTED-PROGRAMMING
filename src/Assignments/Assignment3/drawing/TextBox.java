package Assignments.Assignment3.drawing;

import Assignments.Assignment3.shapes.Rectangle;

import java.awt.*;

public class TextBox extends Rectangle {

    String text;
    boolean leftAlign;

    TextBox(String text, int x, int y, int width, int height){
        super(x, y, width, height);
        this.text = text;
    }

    void paint(Graphics g){

        super.draw(g);

        g.setColor(Color.black);
        g.setFont(new Font(Font.SERIF, Font.PLAIN, height/2));
        FontMetrics m = g.getFontMetrics();
        int stringWidth = m.stringWidth(text);
        int stringHeight = m.getAscent() - m.getDescent();

        if (leftAlign)
            g.drawString(text, x + stringHeight/2, y + height/2 + stringHeight / 2);
        else g.drawString(text, x + width / 2 - stringWidth / 2, y + height/2 + stringHeight / 2);

    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    /**
     * aligns the text to the left of the textbox
     */
    void LEFT_ALIGN(){
        leftAlign = true;
    }

}
