package Assignments.Assignment3.drawing;

import Assignments.Assignment3.shapes.*;
import Assignments.Assignment3.shapes.Rectangle;
import Assignments.Assignment3.shapes.Shape;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Panel extends JPanel implements ActionListener, MouseListener, MouseMotionListener, KeyListener {

    private final int panelX = 2;
    private final int panelY = 2;
    private final int panelWidth = Toolkit.getDefaultToolkit().getScreenSize().width - 4;
    private final int panelHeight = Toolkit.getDefaultToolkit().getScreenSize().height - 28;
    Timer timer;
    int DELAY = 1;

    private Window mainWindow;
    private Window colorWindow;
    private Window fileWindow;

    private int drawingX;
    private int drawingY;
    private int drawingWidth;
    private int drawingHeight;
    private Shape shape;
    private LinkedList<String> files;
    private final String folderPath = "./src/Assignments/Assignment3/files";
    private LinkedList<LinkedList<Shape>> layer;
    private LinkedList<LinkedList<Shape>> redoList;
    private int layerSelected = -1;
    private boolean drawing;
    private int shapeChoice;
    private int x1;
    private int y1;
    private int colorCount = 0;
    private int customCount = 0;

    int[][][] gradientColor;
    private int fade = 255;

    Panel(){
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        setBounds(0, 0, panelWidth, panelHeight);
        setFocusable(true);
        initializeAssets();

        timer = new Timer(DELAY, this);
        timer.start();

        addMouseListener( this );
        addMouseMotionListener(this);
        addKeyListener(this);
    }

    void initializeAssets(){

        initPanel();
        initMainWindow();
        initColorWindow();
        initFileWindow();

        drawingX = panelX;
        drawingY = panelY + mainWindow.toolBars.get(0).getHeight() + mainWindow.toolBars.get(1).getHeight();
        drawingWidth = mainWindow.toolBars.get(1).getWidth() + mainWindow.toolBars.get(2).getWidth() + mainWindow.toolBars.get(3).getWidth();
        drawingHeight = panelHeight - mainWindow.toolBars.get(0).getHeight() - mainWindow.toolBars.get(1).getHeight();

    }

    void initPanel(){

        layer = new LinkedList<>();
        layer.add(new LinkedList<>());
        redoList = new LinkedList<>();
        redoList.add(new LinkedList<>());
        files = new LinkedList<>();

        getFiles();

    }

    void initMainWindow(){

        int menuHeight = 30;
        int toolBarHeight = 155;
        int shapeToolBarWidth = 410;
        int strokeToolBarWidth = 175;
        int colorToolBarWidth = 650;
        int layerToolBarWidth = panelWidth - shapeToolBarWidth - strokeToolBarWidth - colorToolBarWidth;

        mainWindow = new Window(panelX, panelY, panelWidth, menuHeight + toolBarHeight);
        mainWindow.setOpen(true);

        // menu bar
        ToolBar menuBar = new ToolBar("", mainWindow.getX(), mainWindow.getY(), panelWidth, menuHeight, 1, 19);

        LinkedList<Button> buttons1 = new LinkedList<>();
        buttons1.add(new ActiveButton("New"));
        buttons1.add(new ActiveButton("Open"));
        buttons1.add(new ActiveButton("Save"));

        menuBar.addButton(new MenuButton("File", buttons1));

        LinkedList<Button> buttons2 = new LinkedList<>();
        buttons2.add(new ActiveButton("Undo"));
        buttons2.add(new ActiveButton("Redo"));

        menuBar.addButton(new MenuButton("Edit", buttons2));

        menuBar.addButton(new ActiveButton("Undo"));
        menuBar.addButton(new ActiveButton("Redo"));

        mainWindow.toolBars.add(menuBar);

        //shapes toolbar
        ToolBar shapeToolBar = new ToolBar("Shapes" ,mainWindow.getX(), mainWindow.getY() + menuHeight,shapeToolBarWidth, toolBarHeight, 2, 3, 1, 1);

        Button freeDraw = new ToggleButton(new Line(Color.black));
        freeDraw.setText("Free Drawing");
        shapeToolBar.addLeftButton(freeDraw);

        shapeToolBar.addButton(new ToggleButton(new Circle(Color.white)));
        shapeToolBar.addButton(new ToggleButton(new Rectangle(Color.white)));
        shapeToolBar.addButton(new ToggleButton(new RightTriangle(Color.white)));
        shapeToolBar.addButton(new ToggleButton(new EquilateralTriangle(Color.white)));
        shapeToolBar.addButton(new ToggleButton(new Hexagon(Color.white)));
        shapeToolBar.addButton(new ToggleButton(new Pentagram(Color.white)));

        Button eraserButton = new ToggleButton(new Circle(Color.white));
        eraserButton.setText("Eraser");
        shapeToolBar.addRightButton(eraserButton);

        mainWindow.toolBars.add(shapeToolBar);

        // strokeToolBar
        ToolBar strokeToolBar = new ToolBar("Stroke", mainWindow.getX() + shapeToolBarWidth, mainWindow.getY() + menuHeight, strokeToolBarWidth, toolBarHeight, 2, 1, 1, 0);

        // stroke dropdown button
        LinkedList<Button> buttons = new LinkedList<>();
        for (int i = 0; i < 7; i++)
            buttons.add(new ActiveButton("" + i));

        Button strokeButton = new MenuButton("" + 1, buttons);
        strokeToolBar.addLeftButton(strokeButton);

        // increase/decrease stroke buttons
        strokeToolBar.addButton(new ActiveButton("+"));
        strokeToolBar.addButton(new ActiveButton("-"));

        mainWindow.toolBars.add(strokeToolBar);

        // color toolbar
        ToolBar colorToolBar = new ToolBar("Colors", mainWindow.getX() + shapeToolBarWidth + strokeToolBarWidth, mainWindow.getY() + menuHeight,colorToolBarWidth, toolBarHeight, 3, 10, 2, 1);

        // stroke color button
        Button strokeColorButton = new ToggleButton(new Rectangle(Color.black));
        strokeColorButton.setText("Stroke");
        colorToolBar.addLeftButton(strokeColorButton);

        // fill color button
        Button fillColorButton = new ToggleButton(new Rectangle(Color.white));
        fillColorButton.setText("Fill");
        colorToolBar.addLeftButton(fillColorButton);

        // basic colored buttons
        colorToolBar.addButton(new ActiveButton(new Rectangle(new Color(0, 0, 0))));
        colorToolBar.addButton(new ActiveButton(new Rectangle(new Color(128, 128, 128))));
        colorToolBar.addButton(new ActiveButton(new Rectangle(new Color(128, 0, 0))));
        colorToolBar.addButton(new ActiveButton(new Rectangle(new Color(255, 0, 0))));
        colorToolBar.addButton(new ActiveButton(new Rectangle(new Color(255, 165, 0))));
        colorToolBar.addButton(new ActiveButton(new Rectangle(new Color(255, 255, 0))));
        colorToolBar.addButton(new ActiveButton(new Rectangle(new Color(0, 128, 0))));
        colorToolBar.addButton(new ActiveButton(new Rectangle(new Color(0, 0, 255))));
        colorToolBar.addButton(new ActiveButton(new Rectangle(new Color(0, 0, 190))));
        colorToolBar.addButton(new ActiveButton(new Rectangle(new Color(150, 0, 150))));
        colorToolBar.addButton(new ActiveButton(new Rectangle(new Color(255, 255, 255))));
        colorToolBar.addButton(new ActiveButton(new Rectangle(new Color(190, 190, 190))));
        colorToolBar.addButton(new ActiveButton(new Rectangle(new Color(193, 128, 0))));
        colorToolBar.addButton(new ActiveButton(new Rectangle(new Color(244, 194, 194))));
        colorToolBar.addButton(new ActiveButton(new Rectangle(new Color(200, 100, 0))));
        colorToolBar.addButton(new ActiveButton(new Rectangle(new Color(253, 253, 150))));
        colorToolBar.addButton(new ActiveButton(new Rectangle(new Color(128, 255, 0))));
        colorToolBar.addButton(new ActiveButton(new Rectangle(new Color(173, 216, 230))));
        colorToolBar.addButton(new ActiveButton(new Rectangle(new Color(0, 128, 128))));
        colorToolBar.addButton(new ActiveButton(new Rectangle(new Color(255, 0, 255))));

        // empty buttons
        for (int i = 0; i < 10; i++)
            colorToolBar.addButton(new ActiveButton(new Rectangle(Color.white)));

        // stroke color selected by default
        colorToolBar.buttons.get(0).setPressed(true);

        Button editColorButton = new ActiveButton(new Rectangle());
        editColorButton.setText("Edit");
        colorToolBar.addRightButton(editColorButton);

        mainWindow.toolBars.add(colorToolBar);

        // layers toolbar
        ToolBar layerToolBar = new LayerToolBar("Layers", mainWindow.getX() + shapeToolBarWidth + strokeToolBarWidth + colorToolBarWidth, mainWindow.getY() + menuHeight, layerToolBarWidth, panelHeight - menuHeight);
        layerToolBar.TITLE_ON_TOP();

        int spacing = 10;
        int buttonSize = layerToolBarWidth/5;

        layerToolBar.buttons.add(new ActiveButton("+", layerToolBar.getX() + layerToolBarWidth/2 - spacing - buttonSize*2, layerToolBar.getY() + 30 + spacing + buttonSize, buttonSize, buttonSize));
        layerToolBar.buttons.add(new ActiveButton("-", layerToolBar.getX() + layerToolBarWidth/2 - buttonSize, layerToolBar.getY() + 30 + spacing + buttonSize, buttonSize, buttonSize));
        layerToolBar.buttons.add(new ActiveButton("Raise", layerToolBar.getX() + layerToolBarWidth/2 + spacing, layerToolBar.getY() + 30 + spacing + buttonSize, buttonSize*2, buttonSize / 2));
        layerToolBar.buttons.add(new ActiveButton("Lower", layerToolBar.getX() + layerToolBarWidth/2 + spacing, layerToolBar.getY() + 30 + spacing + buttonSize / 2 + buttonSize, buttonSize*2, buttonSize / 2));

        layerToolBar.addButton(new ToggleButton("layer " + 1), 4);

        mainWindow.toolBars.add(layerToolBar);

    }

    void initColorWindow(){

        colorWindow = new Window(panelWidth/2 - 650/2, panelHeight/2 - 600/2, 650, 550);

        int x = colorWindow.getX();
        int y = colorWindow.getY();
        int width = colorWindow.getWidth();
        int height = colorWindow.getHeight();
        int columnWidth = width/16;
        int rowHeight = height/14;
        int spacing = 5;

        ToolBar customColors = new ToolBar("Custom Colors", x + spacing, y + spacing + (rowHeight*9), (columnWidth*8) - spacing*2, (rowHeight*3) - spacing*2, 2, 8);
        customColors.TITLE_ON_TOP();

        for (int i = 0; i < 16; i++)
            customColors.addButton(new ActiveButton(new Rectangle(Color.white)));

        colorWindow.toolBars.add(customColors);
        colorWindow.buttons.add(new ActiveButton("X", x + width - columnWidth, y, columnWidth, rowHeight));
        colorWindow.buttons.add(new ActiveButton("OK", x + spacing, y + height - rowHeight + spacing, 3*(columnWidth - spacing), rowHeight - spacing*2));
        colorWindow.buttons.add(new ActiveButton("Cancel", x + spacing + 3*(columnWidth - spacing) + spacing, y + height - rowHeight + spacing, 3*(columnWidth - spacing), rowHeight - spacing*2));
        colorWindow.buttons.add(new ActiveButton("Add to Custom Colors", x + columnWidth*8 + spacing, y + height - rowHeight + spacing, columnWidth*8 - spacing*2, rowHeight - spacing*2));

        Button colorSelected = new ActiveButton(new Rectangle(Color.white));
        colorSelected.setBounds(x + columnWidth*8 + spacing, y + (rowHeight*9) + spacing, columnWidth*4 - spacing*2, rowHeight*3 - spacing*2);
        colorSelected.setText("Color Selected");
        colorWindow.buttons.add(colorSelected);

        Button colorGradient = new ActiveButton("", x + spacing + 40, y + rowHeight*2 + spacing, 510, 255);
        colorWindow.buttons.add(colorGradient);

        Button fade = new ActiveButton("", x + spacing + 40 + columnWidth*16 - spacing*2 - 120 + spacing + 20, y + rowHeight*2 + spacing, 30, 255);
        colorWindow.buttons.add(fade);

        TextBox title = new TextBox("Edit Colors", x, y, width, rowHeight);
        title.LEFT_ALIGN();

        colorWindow.textBoxes.add(title);
        colorWindow.textBoxes.add(new TextBox("Red:    " + colorSelected.getShape().getFillColor().getRed(), x + columnWidth*12 + spacing, y + rowHeight*9 + spacing, columnWidth*4 - spacing*2, rowHeight - spacing*2));
        colorWindow.textBoxes.add(new TextBox("Green:  " + colorSelected.getShape().getFillColor().getGreen(), x + columnWidth*12 + spacing, y + rowHeight*10 + spacing, columnWidth*4 - spacing*2, rowHeight - spacing*2));
        colorWindow.textBoxes.add(new TextBox("Blue:   " + colorSelected.getShape().getFillColor().getBlue(), x + columnWidth*12 + spacing, y + rowHeight*11 + spacing, columnWidth*4 - spacing*2, rowHeight - spacing*2));

    }

    void initFileWindow(){

        fileWindow = new Window(Toolkit.getDefaultToolkit().getScreenSize().width/2 - 600/2, Toolkit.getDefaultToolkit().getScreenSize().height/2 - 550/2, 600, 550);

        int x = fileWindow.getX();
        int y = fileWindow.getY();
        int width = fileWindow.getWidth();
        int height = fileWindow.getHeight();
        int columnWidth = width/16;
        int rowHeight = height/14;
        int spacing = 5;

        ToolBar filesToolBar = new ToolBar("Files:", x + columnWidth, y + rowHeight*2, columnWidth*14, rowHeight*8, 8, 1, 0, 0);
        filesToolBar.TITLE_ON_TOP();
        filesToolBar.LEFT_ALIGN();

        for (String file : files)
            filesToolBar.addButton(new ActiveButton(file));

        fileWindow.toolBars.add(filesToolBar);

        TextBox fileSelected = new TextBox("File name: ", x + columnWidth, y + rowHeight*11, columnWidth*14, rowHeight);
        fileSelected.LEFT_ALIGN();
        fileWindow.textBoxes.add(fileSelected);

        fileWindow.buttons.add(new ActiveButton("X", x + width - columnWidth, y + 1, columnWidth, rowHeight - 1));
        fileWindow.buttons.add(new ActiveButton("Open", x + width - 6*(columnWidth) + spacing, y + height - rowHeight + spacing, 3*(columnWidth - spacing), rowHeight - spacing*2));
        fileWindow.buttons.add(new ActiveButton("Cancel", x + width - 3*(columnWidth) + spacing, y + height - rowHeight + spacing, 3*(columnWidth - spacing), rowHeight - spacing*2));

        TextBox title = new TextBox("Open File", x, y, width, rowHeight);
        title.LEFT_ALIGN();
        fileWindow.textBoxes.add(title);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // paints a boundary for the drawing region
        g.setColor(Color.white);
        g.fill3DRect(drawingX, drawingY, drawingWidth, drawingHeight, true);
        g.setColor(Color.lightGray);
        g.drawRect(drawingX, drawingY, drawingWidth, drawingHeight);

        drawShapes(g);
        mainWindow.paint(g);

        // paints a non-functional color gradient as an icon for the edit-color-button
        paintColorGradient(mainWindow.toolBars.get(3).buttons.get(32), 127, g);

        if (colorWindow.isOpen()){
            colorWindow.paint(g);
            paintColorGradient(colorWindow.buttons.get(5), fade, g);
            paintFade(colorWindow.buttons.get(6), g);
        }

        if (fileWindow.isOpen())
            fileWindow.paint(g);

    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {

        int x = e.getX();
        int y = e.getY();

        // colorWindow opened
        if (colorWindow.isOpen()){

            colorWindow.isClicked(x, y);

            if (colorWindow.buttons.get(1).isPressed()){
                setColorButton(mainWindow.toolBars.get(3).buttons.get(22 + colorCount%10), colorWindow.buttons.get(4));
                colorCount++;
            } else if (colorWindow.buttons.get(3).isPressed()){
                setColorButton(colorWindow.toolBars.get(0).buttons.get(customCount%16), colorWindow.buttons.get(4));
                customCount++;
            } else if (colorWindow.buttons.get(5).isPressed())
                setColorFromGradient(x, y);
             else if (colorWindow.buttons.get(6).isPressed())
                setFade(x, y);

            for (Button button : colorWindow.toolBars.get(0).buttons) {
                if(button.isPressed())
                    setColorButton(colorWindow.buttons.get(4), button);
            }

            return;

        }

        // fileWindow opened
        if(fileWindow.isOpen()){

            fileWindow.isClicked(x, y);

            if (fileWindow.buttons.get(1).isPressed())
                if (fileWindow.textBoxes.get(0).getText().substring(11).length() > 0) {
                    getShapes("./src/files/" + fileWindow.textBoxes.get(0).getText().substring(11));
                } else System.out.println("No file selected!");


            for (Button button : fileWindow.toolBars.get(0).buttons) {
                if(button.isPressed())
                    fileWindow.textBoxes.get(0).setText("File name: " + button.getText());
            }

            return;

        }

        // file menu dropdown
        if (mainWindow.toolBars.get(0).buttons.get(0).isVisible()){
            for (int i = 0; i < 3; i++) {
                mainWindow.toolBars.get(0).buttons.get(0).buttons.get(i).isClicked(x, y);
                if(mainWindow.toolBars.get(0).buttons.get(0).buttons.get(i).isPressed()){
                    if (i == 0)
                        renew();
                    else if (i ==1)
                        fileWindow.setOpen(true);
                    else saveFile();
                }
            }
            mainWindow.toolBars.get(0).buttons.get(0).setVisible(false);
            return;
        }

        // edit menu dropdown
        if (mainWindow.toolBars.get(0).buttons.get(1).isVisible()) {
            for (int i = 0; i < 2; i++) {
                mainWindow.toolBars.get(0).buttons.get(1).buttons.get(i).isClicked(x, y);
                if(mainWindow.toolBars.get(0).buttons.get(1).buttons.get(i).isPressed()){
                    if (i == 0)
                        undo();
                    else redo();
                }
            }
            mainWindow.toolBars.get(0).buttons.get(1).setVisible(false);
            return;
        }

        // stroke dropdown
        if (mainWindow.toolBars.get(2).buttons.get(0).isVisible()) {
            for (int i = 0; i < 7; i++) {
                mainWindow.toolBars.get(2).buttons.get(0).buttons.get(i).isClicked(x, y);
                if (mainWindow.toolBars.get(2).buttons.get(0).buttons.get(i).isPressed())
                    mainWindow.toolBars.get(2).buttons.get(0).setText("" + (i));
            }
            mainWindow.toolBars.get(2).buttons.get(0).setVisible(false);
            return;
        }

        // stroke color pressed
        if(mainWindow.toolBars.get(3).buttons.get(0).isPressed()){
            for (int i = 2; i < mainWindow.toolBars.get(3).buttons.size() - 3; i++) {
                mainWindow.toolBars.get(3).buttons.get(i).isClicked(x, y);
                if(mainWindow.toolBars.get(3).buttons.get(i).isPressed()) {
                    setColorButton(mainWindow.toolBars.get(3).buttons.get(0), mainWindow.toolBars.get(3).buttons.get(i));
                }
            }
        }

        // fill color pressed
        if(mainWindow.toolBars.get(3).buttons.get(1).isPressed()){
            for (int i = 2; i < mainWindow.toolBars.get(3).buttons.size() - 3; i++) {
                mainWindow.toolBars.get(3).buttons.get(i).isClicked(x, y);
                if(mainWindow.toolBars.get(3).buttons.get(i).isPressed()) {
                    setColorButton(mainWindow.toolBars.get(3).buttons.get(1), mainWindow.toolBars.get(3).buttons.get(i));
                }
            }
        }

        // main window is clicked only if the other windows or the dropdowns are not opened
        mainWindow.isClicked(x, y);

        // edit color button is set as visible
        if (mainWindow.toolBars.get(3).buttons.get(32).isPressed())
            colorWindow.setOpen(true);

        // file menu dropdown is set as visible
        if (mainWindow.toolBars.get(0).buttons.get(0).isPressed())
            mainWindow.toolBars.get(0).buttons.get(0).setVisible(true);

        // edit menu button is set as visible
        if (mainWindow.toolBars.get(0).buttons.get(1).isPressed())
            mainWindow.toolBars.get(0).buttons.get(1).setVisible(true);

        // stroke button is set as visible
        if (mainWindow.toolBars.get(2).buttons.get(0).isPressed())
            mainWindow.toolBars.get(2).buttons.get(0).setVisible(true);

        // undo and redo (active) menu buttons
        if (mainWindow.toolBars.get(0).buttons.get(2).isPressed())
            undo();
        else if (mainWindow.toolBars.get(0).buttons.get(3).isPressed())
            redo();

        // stroke++
        if (mainWindow.toolBars.get(2).buttons.get(1).isPressed()){
            if( Integer.parseInt(mainWindow.toolBars.get(2).buttons.get(0).getText()) == 6)
                System.out.println("Stroke can't be greater than 6!");
            else {
                // increments the value of stroke
                mainWindow.toolBars.get(2).buttons.get(0).setText("" + (Integer.parseInt(mainWindow.toolBars.get(2).buttons.get(0).getText())+1));
            }
        }

        // stroke--
        if (mainWindow.toolBars.get(2).buttons.get(2).isPressed()){
            if(Integer.parseInt(mainWindow.toolBars.get(2).buttons.get(0).getText()) == 0)
                System.out.println("Stroke can't be less than 0!");
            else {
                // decrements the value of stroke
                mainWindow.toolBars.get(2).buttons.get(0).setText("" + (Integer.parseInt(mainWindow.toolBars.get(2).buttons.get(0).getText())-1));
            }
        }

        // shapes selection
        for (int i = 0; i < mainWindow.toolBars.get(1).buttons.size(); i++) {
            if (mainWindow.toolBars.get(1).buttons.get(i).isPressed()){
                shapeChoice = i + 1;
            }
        }

        // layer++
        if (mainWindow.toolBars.get(4).buttons.get(0).isPressed())
            addLayer();

        // layer--
        if (mainWindow.toolBars.get(4).buttons.get(1).isPressed())
            removeLayer(layerSelected + 4);

        // raise layer
        if (mainWindow.toolBars.get(4).buttons.get(2).isPressed())
            raiseLayer(layerSelected + 4);

        // lower layer
        if (mainWindow.toolBars.get(4).buttons.get(3).isPressed())
            lowerLayer(layerSelected + 4);


        // drawing shapes
        if (y > drawingY && x < drawingX + drawingWidth){

            if (SwingUtilities.isLeftMouseButton(e)) {

                if (layer.size() > 0) {

                    if (shapeChoice > 0 && shapeChoice < 9) {

                        drawing = true;

                        switch (shapeChoice) {
                            case 1 -> shape = new Line(mainWindow.toolBars.get(3).buttons.get(1).getShape().getFillColor());
                            case 2 -> shape = new Circle(mainWindow.toolBars.get(3).buttons.get(1).getShape().getFillColor(), mainWindow.toolBars.get(3).buttons.get(0).getShape().getFillColor());
                            case 3 -> shape = new Rectangle(mainWindow.toolBars.get(3).buttons.get(1).getShape().getFillColor(), mainWindow.toolBars.get(3).buttons.get(0).getShape().getFillColor());
                            case 4 -> shape = new RightTriangle(mainWindow.toolBars.get(3).buttons.get(1).getShape().getFillColor(), mainWindow.toolBars.get(3).buttons.get(0).getShape().getFillColor());
                            case 5 -> shape = new EquilateralTriangle(mainWindow.toolBars.get(3).buttons.get(1).getShape().getFillColor(), mainWindow.toolBars.get(3).buttons.get(0).getShape().getFillColor());
                            case 6 -> shape = new Hexagon(mainWindow.toolBars.get(3).buttons.get(1).getShape().getFillColor(), mainWindow.toolBars.get(3).buttons.get(0).getShape().getFillColor());
                            case 7 -> shape = new Pentagram(mainWindow.toolBars.get(3).buttons.get(1).getShape().getFillColor(), mainWindow.toolBars.get(3).buttons.get(0).getShape().getFillColor());
                            case 8 -> shape = new Line(Color.white);
                        }

                        layer.get(0).push(shape);
                        redoList.get(0).clear();

                        if (shapeChoice == 8)
                            shape.setStroke(60);
                        else shape.setStroke(Integer.parseInt(mainWindow.toolBars.get(2).buttons.get(0).getText()));

                        // initial point set
                        x1 = e.getX();
                        y1 = e.getY();
                        shape.setPoint(new Point(x1, y1));
                        shape.addPixel(x, y);

                    } else System.out.println("Select a shape first!");

                } else System.out.println("No layer exists to draw on!");
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        if (SwingUtilities.isLeftMouseButton(e)){

            int x = e.getX();
            int y = e.getY();

            // mouse drag on color gradient
            if (colorWindow.buttons.get(5).isPressed())
                setColorFromGradient(x, y);

            // mouse drag on fade gradient
            if (colorWindow.buttons.get(6).isPressed())
                setFade(x, y);

            // mouse is dragged on drawing region when windows and dropdowns are not open
            if (y > drawingY && x < drawingX + drawingWidth && !colorWindow.isOpen() && !fileWindow.isOpen() && !mainWindow.toolBars.get(2).buttons.get(0).isVisible()){

                if (shape != null) {

                    if (shapeChoice > 0 && shapeChoice < 9) {

                        int tempX = e.getX();
                        int tempY = e.getY();

                        int width = Math.abs(tempX - x1);
                        int height = Math.abs(tempY - y1);

                        // pythagoras theorem to find radius of circle
                        int size = (int) (Math.sqrt((width * width) + (height * height)));

                        // free drawing
                        if (shapeChoice == 1 || shapeChoice == 8) {
                            shape.addPixel(e.getX(), e.getY());
                            return;
                        }

                        // circle/equilateral triangle/pentagram
                        if (shapeChoice == 2 || shapeChoice == 5 || shapeChoice == 7) {
                            shape.setSize(size);
                            shape.setPoint(new Point(x1 - size, y1 - size));
                            return;
                        }

                        // finds top left depending on which quadrant the mouse is dragged into w.r.t the initial point
                        Point topLeft = new Point(x1, y1);
                        if (tempY < y1 && tempX < x1)
                            topLeft = new Point(tempX, tempY);
                        else if (tempY < y1)
                            topLeft = new Point(x1, tempY);
                        else if (tempX < x1)
                            topLeft = new Point(tempX, y1);

                        shape.setPoint(topLeft);
                        shape.setWidth(width);
                        shape.setHeight(height);


                    }
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        if (colorWindow.isOpen()){

            if (colorWindow.buttons.get(0).isPressed() || colorWindow.buttons.get(2).isPressed() || colorWindow.buttons.get(1).isPressed())
                colorWindow.close();

            colorWindow.setPressed(false);

        }

        if (fileWindow.isOpen()){

            if ((fileWindow.buttons.get(0).isPressed() || fileWindow.buttons.get(2).isPressed()) || (fileWindow.buttons.get(1).isPressed() && fileWindow.textBoxes.get(0).getText().substring(11).length() > 0 ))
                fileWindow.close();

            fileWindow.setPressed(false);
        }

        mainWindow.setPressed(false);

        //panel
        if(SwingUtilities.isLeftMouseButton(e)){

            // shape completed
            if (shapeChoice > 0 && shapeChoice < 9) {
                shapeChoice = 0;
                shape = null;
                drawing = false;
            }

        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {

        // returns if key is pressed while shape is being drawn
        if(drawing) {
            System.out.println("Complete drawing the shape first!");
            return;
        }

        int keyCode = e.getKeyCode();

        switch (keyCode) {

            // layer++ (NumPad + key)
            case KeyEvent.VK_ADD -> addLayer();

            // layer-- (NumPad - key)
            case KeyEvent.VK_SUBTRACT -> removeLayer(layerSelected + 4);

            // raise layer (up arrow key)
            case KeyEvent.VK_UP -> raiseLayer(layerSelected + 4);

            // lower layer (down arrow key)
            case KeyEvent.VK_DOWN -> lowerLayer(layerSelected + 4);

            // new page
            case 'N' -> renew();

            // opens file window
            case 'F' -> {
                if (!colorWindow.isOpen())
                    fileWindow.setOpen(true);
            }

            case 'S' -> saveFile();

            case 'Z' -> undo();

            case 'Y' -> redo();

            // opens color gradient window
            case 'C' -> {
                if (!fileWindow.isOpen())
                    colorWindow.setOpen(true);
            }

            // esc key closes any opened window and the program otherwise
            case 27 -> {
                if (colorWindow.isOpen()) {
                    colorWindow.close();
                } else if (fileWindow.isOpen()) {
                    fileWindow.close();
                } else {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
            }

            default -> System.out.println("Invalid Key Pressed!");

        }


    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {

        // sentinel value for layerSelected
        layerSelected = -1;
        for (int i = 4; i < mainWindow.toolBars.get(4).buttons.size(); i++) {
            if (mainWindow.toolBars.get(4).buttons.get(i).isPressed()){
                layerSelected = i - 4;
            }
        }

        Toolkit.getDefaultToolkit().sync();
        repaint();
    }

    /**
     * undoes the shape last drawn
     */
    public void undo(){

        // undo
        if(drawing){
            System.out.println("Complete drawing the shape first!");
            return;
        }

        if (layerSelected == -1){
            System.out.println("Please select a layer!");
            return;
        }

        if (layer.get(layerSelected).isEmpty()) {
            System.out.println("Undo List Empty!");
            return;
        }

        redoList.get(layerSelected).push(layer.get(layerSelected).pop());
        System.out.println("Undone!");

    }

    /**
     * redoes the shape last undone
     */
    public void redo(){

        // redo
        if(drawing){
            System.out.println("Complete drawing the shape first!");
            return;
        }

        if (layerSelected == -1){
            System.out.println("Please select a layer!");
            return;
        }

        if (redoList.get(layerSelected).isEmpty()) {
            System.out.println("Redo List Empty!");
            return;
        }

        layer.get(layerSelected).push(redoList.get(layerSelected).pop());
        System.out.println("Redone!");

    }

    void drawShapes(Graphics g){

        for (int i = layer.size() - 1; i >= 0; i--) {
            for (int j = layer.get(i).size() - 1; j >= 0 ; j--)
                layer.get(i).get(j).draw(g);
        }

    }

    /**
     * gets the name of all the files in the files folder and stores them in the files list
     */
    private void getFiles(){

        File folder = new File(folderPath);
        String[] fileName = folder.list();

        assert fileName != null;
        files.addAll(Arrays.asList(fileName));

    }

    /**
     * saves file with the current time stamp
     */
    private void saveFile(){

        if (files.size() < 8) {

            try {

                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy ~ HH mm ss");
                String timeStamp = df.format(new Date());

                String filePath = folderPath + "/" + timeStamp + ".txt";

                File file = new File(filePath);
                file.createNewFile();
                storeShapes(filePath);

                files.add(timeStamp + ".txt");

                fileWindow.toolBars.get(0).addButton(new ActiveButton(timeStamp + ".txt"));

            } catch (IOException e){

                System.out.println("File not found!");
                e.printStackTrace();

            }

        }else System.out.println("Save slots full!");

    }

    /**
     * creates a list of layers containing shapes from the text file
     */
    private void getShapes(String filePath){

        try {

            File file = new File(filePath);
            Scanner input = new Scanner(file);

            Shape shape = null;
            String data;
            String[] temp;

            renew();
            layer = new LinkedList<>();
            redoList = new LinkedList<>();

            int i = -1;

            while (input.hasNextLine()) {

                String shapeName = input.nextLine();

                if (shapeName.startsWith("layer")) {
                    addLayer();
                    input.nextLine();
                    i++;
                    continue;
                }

                data = input.nextLine();
                temp = data.split(",");
                Color fillColor = new Color(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2]));

                switch (shapeName) {

                    case "Line" -> shape = new Line(fillColor);
                    case "Circle" -> shape = new Circle(fillColor);
                    case "Rectangle" -> shape = new Rectangle(fillColor);
                    case "Equilateral Triangle" -> shape = new EquilateralTriangle(fillColor);
                    case "Right Triangle" -> shape = new RightTriangle(fillColor);
                    case "Hexagon" -> shape = new Hexagon(fillColor);
                    case "Pentagram" -> shape = new Pentagram(fillColor);

                }

                if (shapeName.equals("Line")){
                    shape.setStroke(Integer.parseInt(input.nextLine()));
                    data = input.nextLine();
                    while (data.length() > 0){
                        temp = data.split(",");
                        shape.addPixel(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
                        data = input.nextLine();
                    }

                } else {

                    data = input.nextLine();
                    temp = data.split(",");
                    shape.setStrokeColor(new Color(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2])));
                    shape.setStroke(Integer.parseInt(input.nextLine()));
                    data = input.nextLine();
                    temp = data.split(",");
                    shape.setPoint(new Point(Integer.parseInt(temp[0]), Integer.parseInt(temp[1])));

                    if (shapeName.equals("Circle") || shapeName.equals("Pentagram") || shapeName.equals("Equilateral Triangle"))
                        shape.setSize(Integer.parseInt(input.nextLine()));
                    else {
                        shape.setWidth(Integer.parseInt(input.nextLine()));
                        shape.setHeight(Integer.parseInt(input.nextLine()));
                    }

                }

                layer.get(i).add(shape);

                if (input.hasNextLine() && !shapeName.equals("Line"))
                    input.nextLine();

            }

            input.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        }

    }

    /**
     * stores all the shapes in a text file
     */
    private void storeShapes(String filePath){

        try{

            FileWriter fWriter = new FileWriter(filePath, false);
            String finalData = "";

            // arranges the states such that the last shape drawn is in the end
            for (int i = 0; i < layer.size(); i++) {
                finalData += "layer " + i + "\n\n";
                for (Shape shape : layer.get(i)) {
                    finalData += shape.toString() + '\n';
                }
            }


            fWriter.write(finalData);
            fWriter.close();

        } catch (IOException ex){
            System.out.println("File not found!");
            ex.printStackTrace();
        }

    }

    /**
     * paints a color gradient on the button
     */
    private void paintColorGradient(Button button, int fade, Graphics g) {

        int x;
        int y;
        int width;
        int height;

        if (button.getShape() != null) {
            x = button.getShape().getX();
            y = button.getShape().getY();
            width = button.getShape().getWidth();
            height = button.getShape().getHeight();
        } else {
            x = button.getX();
            y = button.getY();
            width = button.getWidth();
            height = button.getHeight();
        }

        gradientColor = new int[width][height][3];

        for (int j = 0; j < height; j++) {

            for (int i = 0; i < width; i++) {

                double red = 255.0;
                double green = 255.0;
                double blue = 255.0;

                int interval = width / 6;
                double step = 255.0 / interval;

                if (i < interval) {
                    blue = 0;
                    green = (int) (i * step);
                } else if (i < interval * 2) {
                    blue = 0;
                    red = 255 - (int) ((i - interval) * step);
                } else if (i < interval * 3) {
                    red = 0;
                    blue = (int) ((i - interval * 2) * step);
                } else if (i < interval * 4) {
                    red = 0;
                    green = 255 - (int) ((i - interval * 3) * step);
                } else if (i < interval * 5) {
                    green = 0;
                    red = (int) ((i - interval * 4) * step);
                } else if (i < interval * 6){
                    green = 0;
                    blue = 255 - (int) ((i - interval * 5) * step);
                }


                double changeRed = Math.abs((red - fade)/height);
                if (red >= fade)
                    red = (int)(red - (changeRed*j));
                else red = (int)(red + (changeRed*j));

                double changeGreen = Math.abs((green - fade)/height);
                if (green >= fade)
                    green = (int)(green - (changeGreen*j));
                else green = (int)(green + (changeGreen*j));

                double changeBlue = Math.abs((blue - fade)/height);
                if (blue >= fade)
                    blue = (int)(blue - (changeBlue*j));
                else blue = (int)(blue + (changeBlue*j));

                g.setColor(new Color((int)red, (int)green, (int)blue));
                g.fillRect(x + i, y + j, 1, 1);

                gradientColor[i][j][0] = (int)red;
                gradientColor[i][j][1] = (int)green;
                gradientColor[i][j][2] = (int)blue;

            }
        }
    }

    /**
     * sets the color from the color gradient
     */
    private void setColorFromGradient(int x, int y){

        int X = x - colorWindow.buttons.get(5).getX();
        int Y = y - colorWindow.buttons.get(5).getY();

        if (X >= 0 && X < 510 && Y >= 0 && Y < 254){
            colorWindow.buttons.get(4).getShape().setFillColor(new Color(gradientColor[X][Y][0], gradientColor[X][Y][1], gradientColor[X][Y][2]));
            colorWindow.textBoxes.get(1).setText("Red:    " + gradientColor[X][Y][0]);
            colorWindow.textBoxes.get(2).setText("Green:  " + gradientColor[X][Y][1]);
            colorWindow.textBoxes.get(3).setText("Blue:   " + gradientColor[X][Y][2]);
        }

    }

    /**
     * paints a slider for setting the fade on the color gradient
     */
    private void paintFade(Button button, Graphics g){

        int x = button.getX();
        int y = button.getY();
        int width = button.getWidth();
        int height = button.getHeight();

        for (int i = 0; i < height; i++) {
            g.setColor(new Color(255 - i, 255 - i, 255 - i));
            g.fillRect(x, y + i, width, 1);
        }

        g.setColor(Color.red);
        g.fillRect(x, y + (255 - fade), width, 1);

    }

    /**
     * sets the fade from the fade gradient
     */
    private void setFade(int x, int y){

        int X = x - colorWindow.buttons.get(6).getX();
        int Y = y - colorWindow.buttons.get(6).getY();

        if (X >= 0 && X <= 30 && Y >= 0 && Y < 254){
            fade = 255 - Y;
        }
    }

    /**
     * sets the color of first button to the color of the second button
     * @param b1 is the first button
     * @param b2 is the second button
     */
    private void setColorButton(Button b1, Button b2){
        b1.getShape().setFillColor(b2.getShape().getFillColor());
    }

    /**
     * adds a layer at the bottom of all layers
     */
    private void addLayer(){
        if (mainWindow.toolBars.get(4).buttons.size() < 14) {
            layer.add(new LinkedList<>());
            redoList.add(new LinkedList<>());
            mainWindow.toolBars.get(4).addButton(new ToggleButton("layer " + layer.size()), mainWindow.toolBars.get(4).buttons.size());
        } else System.out.println("Can not add more than 10 layers!");
    }

    /**
     * removes the layer and its button at index i
     */
    private void removeLayer(int i){

        if (i < 4){
            System.out.println("Please select a layer!");
            return;
        }

        for (int j = 4; j < mainWindow.toolBars.get(4).buttons.size(); j++) {
            if (mainWindow.toolBars.get(4).buttons.get(i).isPressed()){
                break;
            }
        }
        mainWindow.toolBars.get(4).removeButton(i);
        layer.remove(i - 4);
        redoList.remove(i - 4);
    }

    /**
     * raises the layer and its button at index i
     */
    private void raiseLayer(int i){
        if (i > 4){
            mainWindow.toolBars.get(4).addButton(mainWindow.toolBars.get(4).buttons.remove(i), i - 1);
            Collections.swap(layer, i - 4, i - 4 - 1);
            Collections.swap(redoList, i - 4, i - 4 - 1);
        } else if (i < 4){
            System.out.println("Please select a layer!");
        } else System.out.println("Button can not be raised further!");
    }

    /**
     * lowers layer and its button at index i
     */
    private void lowerLayer(int i){
        if (i < 4){
            System.out.println("Please select a layer!");
            return;
        }
        if (i < mainWindow.toolBars.get(4).buttons.size() - 1){
            raiseLayer(i + 1);
        } else System.out.println("Button can not be lowered further!");
    }

    /**
     * refreshes the page by clearing all the shapes and layers
     */
    private void renew(){
        if (mainWindow.toolBars.get(4).buttons.size() > 4) {
            mainWindow.toolBars.get(4).buttons.subList(4, mainWindow.toolBars.get(4).buttons.size()).clear();
        }
        layer.clear();
        redoList.clear();
    }

}
