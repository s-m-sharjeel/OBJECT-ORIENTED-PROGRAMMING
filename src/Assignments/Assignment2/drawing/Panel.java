package Assignments.Assignment2.drawing;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;
import Assignments.Assignment2.drawing.Shape.*;

public class Panel extends JPanel implements MouseListener, MouseMotionListener, KeyListener{

    private final String filePath;
    private final int panelWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    private final int panelHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    private Graphics g;
    private Color bgColor;
    private boolean darkMode;
    private Shape shape;
    private boolean drawing;
    private int choice;
    private Color color;
    private int clicks;
    private Point topLeft = new Point(0, 0);
    private int x1, y1;
    private int size;
    private int width;
    private int length;
    private final int[] x = new int[3];
    private final int[] y = new int[3];
    private int tempX, tempY;

    public Panel(String filePath) {

        this.filePath = filePath;
        setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());

        bgColor = Color.white;
        setBackground(bgColor);

        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(this);
        setFocusable(true);

        // creates a stack of shapes from the text file
        getShapes();

    }

    private void setUpDrawingGraphics() {

        g = getGraphics();

        // refreshes entire screen only when shape is entirely drawn to prevent constant flickering
        if(clicks == 0){
            g.setColor(bgColor);
            g.fillRect(0, 0, panelWidth, panelHeight);
        }

        // draws shapes from the stack repeatedly to implement layers and overlapping
        drawShapes();

        // writes the name of the shape in the end so that it doesn't get buried under the shapes
        writeText();

    }

    @Override
    public void mouseEntered(MouseEvent e) {setUpDrawingGraphics();}

    @Override
    public void mousePressed(MouseEvent e) {

        if(SwingUtilities.isLeftMouseButton(e)) {

            // circle or rectangle
            if(choice==1 || choice==2) {

                drawing = true;

                // shape created as a circle or rectangle
                if(choice == 1)
                    shape = new Circle(color);
                else shape = new Rectangle(color);

                Shapes.push(shape);
                RedoList.purge();

                // initial point set
                x1 = e.getX();
                y1 = e.getY();
                shape.setPoint(new Point(x1, y1));
                clicks++;

                setUpDrawingGraphics();

            }

        } else if (SwingUtilities.isRightMouseButton(e)) {

            // undo
            if(drawing){
                JOptionPane.showMessageDialog(null, "Complete drawing the shape first!");
            }else{
                if (Shapes.length == 0) {
                    JOptionPane.showMessageDialog(null, "Undo List Empty!");
                    setUpDrawingGraphics();
                    return;
                }

                RedoList.push(Shapes.pop());
                setUpDrawingGraphics();
            }

        } else if(SwingUtilities.isMiddleMouseButton(e)) {

            // redo
            if(drawing){
                JOptionPane.showMessageDialog(null, "Complete drawing the shape first!");
            }else {
                if (RedoList.length == 0) {
                    JOptionPane.showMessageDialog(null, "Redo List Empty!");
                    setUpDrawingGraphics();
                    return;
                }

                Shapes.push(RedoList.pop());
                setUpDrawingGraphics();

            }
        }

    }

    @Override
    public void mouseDragged(MouseEvent e) {

        if (SwingUtilities.isLeftMouseButton(e)){

            if(choice==1 || choice==2) {

                // paints over (erases) previous circle/rectangle
                g.setColor(bgColor);
                if (choice == 1) {
                    g.fillOval(topLeft.x, topLeft.y, size * 2, size * 2);
                } else {
                    g.fillRect(topLeft.x, topLeft.y, width, length);
                }

                tempX = e.getX();
                tempY = e.getY();

                width = Math.abs(tempX - x1);
                length = Math.abs(tempY - y1);

                if (choice == 1) {

                    // pythagoras theorem to find radius of circle
                    size = (int) (Math.sqrt((width * width) + (length * length)));
                    topLeft = new Point(x1 - size, y1 - size);
                    shape.setSize(size);
                    shape.setPoint(topLeft);

                } else {

                    // finds top left depending on which quadrant the mouse is dragged into w.r.t the initial point
                    topLeft = new Point(x1, y1);
                    if (tempY < y1 && tempX < x1)
                        topLeft = new Point(tempX, tempY);
                    else if (tempY < y1)
                        topLeft = new Point(x1, tempY);
                    else if (tempX < x1)
                        topLeft = new Point(tempX, y1);

                    shape.setPoint(topLeft);
                    shape.setWidth(width);
                    shape.setLength(length);

                }

                shape.draw(g);
                setUpDrawingGraphics();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        if(SwingUtilities.isLeftMouseButton(e)){

            // circle or rectangle completed
            if (choice == 1 || choice == 2) {
                clicks = 0;
                color = randomColor();
                setUpDrawingGraphics();
                drawing = false;
            }

        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if(SwingUtilities.isLeftMouseButton(e)) {

            // triangle
            if (choice == 3) {

                // shape created as a triangle
                if (clicks == 0) {
                    drawing = true;
                    shape = new Triangle(color);
                    Shapes.push(shape);
                    RedoList.purge();
                }

                // vertices saved
                x[clicks] = e.getX();
                y[clicks] = e.getY();
                clicks++;

                // triangle completed
                if (clicks == 3) {

                    clicks = 0;

                    // vertices of triangle deep copied to the shape object
                    int[] tempX = new int[3];
                    int[] tempY = new int[3];
                    for (int i = 0; i < 3; i++) {
                        tempX[i] = x[i];
                        tempY[i] = y[i];
                    }
                    shape.setX(tempX);
                    shape.setY(tempY);

                    color = randomColor();
                    setUpDrawingGraphics();
                    drawing = false;

                }
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

        if(choice == 3 && clicks > 0){

            // paints over (erases) the previous line(s)
            drawLines(bgColor);

            tempX = e.getX();
            tempY = e.getY();

            // draws the lines w.r.t the current location of the cursor
            drawLines(color);

            setUpDrawingGraphics();

            // draws line again so that it does not get buried under the shapes
            drawLines(color);

        }
    }

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {

        char c = e.getKeyChar();

        // returns if key is pressed while shape is being drawn
        if(drawing) {
            JOptionPane.showMessageDialog(null, "Complete drawing the shape first!");
            return;
        }

        if(c == '1' || c == '2' || c == '3'){
            color = randomColor();
        }

        // listens to key presses
        switch (c) {

            case '1' -> choice = 1;
            case '2' -> choice = 2;
            case '3' -> choice = 3;

            // undo (alternative to left mouse button)
            case 'z' -> {

                if (Shapes.length == 0) {
                    JOptionPane.showMessageDialog(null, "Undo List Empty!");
                    setUpDrawingGraphics();
                    return;
                }

                RedoList.push(Shapes.pop());
            }

            // redo (alternative to middle mouse button)
            case 'y' -> {

                if (RedoList.length == 0) {
                    JOptionPane.showMessageDialog(null, "Redo List Empty!");
                    setUpDrawingGraphics();
                    return;
                }

                Shapes.push(RedoList.pop());
            }

            // gets a color for the shape from the user
            case 'c' -> color = JColorChooser.showDialog(null, "Pick a color for the shape: ", randomColor());

            // enables/disables dark mode
            case 'd' -> darkMode = !darkMode;


            // displays navigation controls
            case 'x' -> JOptionPane.showMessageDialog(null, "Navigation Controls:\n\n1 -> Circle!\n2 -> Rectangle!\n3 -> Triangle!\nZ -> Undo!\nY -> Redo!\nC -> Choose Shape Color!\nD -> Toggle Dark Mode!\n\n");

            default -> JOptionPane.showMessageDialog(null, "Invalid Key Pressed!");

        }

        if(darkMode)
            bgColor = Color.black;
        else bgColor = Color.white;

        setUpDrawingGraphics();

    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    /**
     * @return a random color
     */
    private Color randomColor(){ return new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)); }

    /**
     * draws all the shapes from the stack on the panel
     */
    private void drawShapes(){

        Shape[] shapes = new Shape[Shapes.length];
        Node current = Shapes.head;

        for (int i = 0; i < Shapes.length; i++) {
            shapes[i] = current.shape;
            current = current.next;
        }

        for (int i = shapes.length - 1; i >= 0; i--)
            shapes[i].draw(g);

    }

    /**
     * writes the name of the shape that has been selected to be drawn and an instruction
     */
    private void writeText(){

        // writes shape name
        g.setColor(color);
        Font font = new Font(Font.MONOSPACED, Font.BOLD, 30);
        g.setFont(font);
        String shapeName;
        if(choice == 1)
            shapeName = "Circle";
        else if (choice == 2)
            shapeName = "Rectangle";
        else if (choice == 3)
            shapeName = "Triangle";
        else shapeName = "";
        g.drawString(shapeName, 50, panelHeight - 125);

        // writes an instruction
        if (darkMode)
            g.setColor(Color.white);
        else g.setColor(Color.black);
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 10));
        g.drawString("Press X to view controls", 50, panelHeight - 100 );

    }

    /**
     * draws lines w.r.t the number of clicks
     * @param color is the color of the lines
     */
    private void drawLines(Color color){
        g.setColor(color);
        if(clicks == 1)
            g.drawLine(x[0], y[0], tempX, tempY);
        else {
            g.drawLine(x[0], y[0], x[1], y[1]);
            g.drawLine(x[1], y[1], tempX, tempY);
            g.drawLine(x[0], y[0], tempX, tempY);
        }
    }

    /**
     * creates a stack of shapes from the text file
     */
    private void getShapes(){

        try {

            File file = new File(filePath);
            Scanner input = new Scanner(file);

            Shape shape;
            String data;
            String[] temp;

            while (input.hasNextLine()) {

                String shapeName = input.nextLine();

                switch (shapeName) {
                    case "Circle" -> {
                        data = input.nextLine();
                        temp = data.split(",");
                        shape = new Circle((new Color(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2]))));
                        data = input.nextLine();
                        temp = data.split(",");
                        shape.setPoint(new Point(Integer.parseInt(temp[0]), Integer.parseInt(temp[1])));
                        shape.setSize(Integer.parseInt(input.nextLine()));
                        Shapes.push(shape);
                    }
                    case "Rectangle" -> {
                        data = input.nextLine();
                        temp = data.split(",");
                        shape = new Rectangle((new Color(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2]))));
                        data = input.nextLine();
                        temp = data.split(",");
                        shape.setPoint(new Point(Integer.parseInt(temp[0]), Integer.parseInt(temp[1])));
                        shape.setWidth(Integer.parseInt(input.nextLine()));
                        shape.setLength(Integer.parseInt(input.nextLine()));
                        Shapes.push(shape);
                    }
                    case "Triangle" -> {
                        data = input.nextLine();
                        temp = data.split(",");
                        shape = new Triangle((new Color(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2]))));
                        int[] tempX = new int[3];
                        int[] tempY = new int[3];
                        for (int i = 0; i < 3; i++) {
                            data = input.nextLine();
                            temp = data.split(",");
                            tempX[i] = Integer.parseInt(temp[0]);
                            tempY[i] = Integer.parseInt(temp[1]);
                        }
                        shape.setX(tempX);
                        shape.setY(tempY);
                        Shapes.push(shape);
                    }
                }

                if (input.hasNextLine())
                    input.nextLine();

            }

            input.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }

    }

    /**
     * stores all the shapes in a text file
     */
    public void storeShapes(){

        // runs when the program is closed
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {

            try{

                FileWriter fWriter = new FileWriter(filePath, false);
                String finalData = "";

                // arranges the states such that the last shape drawn is in the end
                while (Shapes.head != null)
                    finalData = Shapes.pop().toString() + '\n' + finalData;

                fWriter.write(finalData);
                fWriter.close();

            } catch (IOException ex){
                System.out.println("File not found!");
                ex.printStackTrace();
            }

        }));

    }

}
