package Assignments.Assignment3.drawing;

import Assignments.Assignment3.shapes.Rectangle;

import java.awt.*;
import java.util.LinkedList;

public class ToolBar extends Rectangle {

    private final String title;
    private int rows = 1;
    private int columns = 1;
    private int buttonWidth;
    private int buttonHeight;
    private int leftButtons;
    private int rightButtons;
    protected final int spacing = 5;
    final LinkedList<Button> buttons = new LinkedList<>();
    boolean pressed;
    private boolean titleOnTop;
    private boolean leftAlign;

    public ToolBar(String title, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.title = title;
        initToolBar();
    }

    public ToolBar(String title, int x, int y, int width, int height, int rows, int columns, int leftButtons, int rightButtons) {

        super(x, y, width, height);
        this.title = title;
        this.rows = rows;
        this.columns = columns;
        this.leftButtons = leftButtons;
        this.rightButtons = rightButtons;
        initToolBar();
    }

    public ToolBar(String title, int x, int y, int width, int height, int rows, int columns) {

        super(x, y, width, height);
        this.title = title;
        this.rows = rows;
        this.columns = columns;
        initToolBar();
    }

    private void initToolBar() {

        int titleHeight = 0;
        if(title.length()>0)
            titleHeight = 30;

        buttonWidth = (width - spacing*(columns + 1 + leftButtons + rightButtons))/(columns + leftButtons*2 + rightButtons*2);
        buttonHeight = ((height - spacing*(rows + 1) - titleHeight)/rows);

    }

    public void paint(Graphics g) {

        super.draw(g);

        if(title.length() > 0){
            g.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
            FontMetrics m = g.getFontMetrics();
            int stringWidth = m.stringWidth(title);
            int stringHeight = m.getAscent() - m.getDescent();
            if (titleOnTop){
                g.setColor(Color.lightGray);
                g.drawLine(x + 10, y + 30, x + width - 10, y + 30);
                g.setColor(Color.black);
                if (leftAlign)
                    g.drawString(title, x + stringHeight/2, y + 15 + stringHeight / 2);
                else g.drawString(title, x + width / 2 - stringWidth / 2, y + 15 + stringHeight / 2);
            }else {
                g.setColor(Color.lightGray);
                g.drawLine(x + 10, y + height - 30, x + width - 10, y + height - 30);
                g.setColor(Color.black);
                if (leftAlign)
                    g.drawString(title, x + stringHeight/2, y + height - 15 + stringHeight / 2);
                else g.drawString(title, x + width / 2 - stringWidth / 2, y + height - 15 + stringHeight / 2);
            }
        }

        paintButtons(g);

    }

    private void paintButtons(Graphics g) {

        for (Button button: buttons) {
            button.paint(g);
            if (button instanceof MenuButton) {
                for (Button menuButton : button.buttons) {
                    if (menuButton.isPressed())
                        menuButton.paint(g);
                }
            }
        }
    }

    public void isClicked(int x, int y) {

        if(x > this.x && x < this.x + width && y > this.y && y < this.y + height) {

            pressed = true;

            // gets the index of the toggle button pressed if any so that it is later depressed after any click on the toolbar
            int index = -1;
            for (int i = 0; i < buttons.size(); i++) {
                if(buttons.get(i).isPressed() && buttons.get(i) instanceof ToggleButton) {
                    index = i;
                }
            }

            // buttons pressed
            for (int i = 0; i < buttons.size(); i++) {
                buttons.get(i).isClicked(x, y);
                if (i != index && index != -1 && buttons.get(i) instanceof ToggleButton && buttons.get(i).isPressed()){
                    // button previously toggled is depressed
                    buttons.get(index).setPressed(false);
                }
            }
        }
    }

    /**
     * adds a button to the list of buttons
     */
    public void addButton(Button button){

        int titleHeight = 0;
        if (titleOnTop)
            titleHeight = 30;

        button.setBounds(x + ((leftButtons * (buttonWidth*2 + spacing)) + spacing + (buttonWidth + spacing) * ((buttons.size() - leftButtons) % columns)), y + titleHeight + spacing + ((buttonHeight + spacing) * ((buttons.size() - leftButtons) / columns)), buttonWidth, buttonHeight);
        if (button instanceof MenuButton) {
            for (int i = 0; i < button.buttons.size(); i++) {
                button.buttons.get(i).setBounds(button.getX(), button.getY() + spacing + buttonHeight + (25 * i), 200, 25);
            }
        }
        buttons.add(button);

    }

    /**
     * adds a button to the list of buttons at some index i
     */
    public void addButton(Button button, int index){

        int rowHeight = height/10;

        buttons.add(index, button);

        for (int i = index; i < buttons.size(); i++)
            buttons.get(i).setBounds((x) + spacing, (y) + spacing + rowHeight*(i-4), (width) - spacing*2, rowHeight - spacing*2);

    }

    /**
     * removes the button from the list of buttons and sets the bounds of the buttons accordingly
     */
    public void removeButton(int index){

        for (int i = buttons.size() - 1; i > index; i--)
            buttons.get(i).setBounds(buttons.get(i - 1).getX(), buttons.get(i - 1).getY(), buttons.get(i - 1).getWidth(), buttons.get(i - 1).getHeight());

        buttons.remove(index);

    }

    /**
     * adds a button to the left of the toolbar
     */
    public void addLeftButton(Button button){

        int titleHeight = 0;
        if (titleOnTop)
            titleHeight = 30;

        button.setBounds(x + spacing + ((buttonWidth*2 + spacing) * buttons.size()), y + titleHeight + spacing, buttonWidth*2, buttonHeight*rows + (spacing * (rows - 1)));
        if (button instanceof MenuButton) {
            for (int i = 0; i < button.buttons.size(); i++) {
                button.buttons.get(i).setBounds(button.getX(), button.getY() + spacing*2 + buttonHeight*2 + (25 * i), buttonWidth*2, 25);
            }
        }
        buttons.add(button);

    }

    /**
     * adds a button to the right of the toolbar
     */
    public void addRightButton(Button button){

        int titleHeight = 0;
        if (titleOnTop)
            titleHeight = 30;

        button.setBounds(x + width - buttonWidth*2 - spacing - ((buttonWidth*2 + spacing)) * ((buttons.size() - leftButtons - (rows*columns))), y + titleHeight + spacing, buttonWidth*2, buttonHeight*rows + (spacing * (rows - 1)));
        if (button instanceof MenuButton) {
            for (int i = 0; i < button.buttons.size(); i++) {
                button.buttons.get(i).setBounds(button.getX(), button.getY() + spacing*2 + buttonHeight*2 + (25 * i), buttonWidth*2, 25);
            }
        }
        buttons.add(button);

    }

    /**
     * title is set to be displayed at the top of the toolbar which is by-default displayed at the bottom
     */
    public void TITLE_ON_TOP(){
        titleOnTop = true;
    }

    /**
     * aligns text to the left in the toolbar
     */
    public void LEFT_ALIGN(){
        leftAlign = true;
        for (Button button: buttons) {
            button.LEFT_ALIGN();
            if (button instanceof MenuButton) {
                for (Button menuButton : button.buttons) {
                    if (menuButton.isPressed())
                        menuButton.LEFT_ALIGN();
                }
            }
        }
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;

        for (Button button: buttons) {
            if (button instanceof ActiveButton)
                button.setPressed(pressed);
            if (button instanceof MenuButton) {
                if (!button.isVisible())
                    button.setPressed(false);
                for (Button menuButton : button.buttons) {
                    menuButton.setPressed(pressed);
                }
            }
        }

    }
}
