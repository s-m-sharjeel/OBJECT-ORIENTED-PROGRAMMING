package Assignments.Assignment3.drawing;

public class LayerToolBar extends ToolBar{

    public LayerToolBar(String title, int x, int y, int width, int height) {
        super(title, x, y, width, height);
    }

    /**
     * adds a button to the list of buttons at index i and sets its bounds according to the layer buttons
     */
    @Override
    public void addButton(Button button, int index) {

        int rowHeight = (height - 30 - (width/5)*4)/10;

        buttons.add(index, button);

        for (int i = index; i < buttons.size(); i++)
            buttons.get(i).setBounds((x + 10) + spacing, (y + 30 + (width/5)*3) + spacing + rowHeight*(i-4), (width - 10*2) - spacing*2, rowHeight - spacing*2);

    }

}
