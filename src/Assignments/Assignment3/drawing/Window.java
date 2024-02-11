package Assignments.Assignment3.drawing;

import Assignments.Assignment3.shapes.Rectangle;
import java.awt.*;
import java.util.LinkedList;

public class Window extends Rectangle{

    LinkedList<ToolBar> toolBars = new LinkedList<>();

    LinkedList<Button> buttons = new LinkedList<>();

    LinkedList<TextBox> textBoxes = new LinkedList<>();

    private boolean open;

    private boolean pressed;

    public Window(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void paint(Graphics g) {

        super.draw(g);

        paintTextBoxes(g);
        paintButtons(g);
        paintToolBars(g);

    }

    private void paintTextBoxes(Graphics g) {
        for (TextBox tb : textBoxes)
            tb.paint(g);
    }

    private void paintButtons(Graphics g){
        for (Button button : buttons)
            button.paint(g);
    }

    private void paintToolBars(Graphics g){
        for (int i = toolBars.size() - 1; i >= 0; i--)
            toolBars.get(i).paint(g);
    }

    public void isClicked(int x, int y){

        for (ToolBar toolBar : toolBars)
            toolBar.isClicked(x, y);

        for (Button button : buttons)
            button.isClicked(x, y);
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;

        for (ToolBar toolBar : toolBars)
            toolBar.setPressed(false);

        for (Button button : buttons)
            button.setPressed(false);

    }

    public void close(){
        setPressed(false);
        setOpen(false);
    }
}
