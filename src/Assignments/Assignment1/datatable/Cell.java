package Assignments.Assignment1.datatable;

import java.awt.*;
import java.awt.Point;

class Cell {

    protected java.awt.Point topLeft;
    protected int width;
    protected int height;
    protected int stroke = 4;
    protected Color cell_color;
    protected Color stroke_color;
    protected String text;
    protected Color fontColor;
    protected int fontSize;
    protected int fontStyle;
    protected Font font;
    Cell(int x, int y, int width, int height, Color cell_color, Color stroke_color, Color fontColor, int stroke, String text){

        topLeft = new Point(x, y);
        this.width = width;
        this.height = height;
        this.cell_color = cell_color;
        this.stroke_color = stroke_color;
        this.stroke = stroke;
        this.text = text;
        this.fontColor = fontColor;
        this.fontSize = height/2;

    }

    /**
     * Paints a cell with certain text after adjusting its size.
     * @param g
     */
    void paint(Graphics g){

        g.setColor(stroke_color);
        g.fillRect(topLeft.x - stroke , topLeft.y - stroke, width + stroke, height + stroke);

        g.setColor(cell_color);
        g.fillRect(topLeft.x , topLeft.y, width - stroke, height - stroke);

        g.setColor(fontColor);
        font = new Font("SansSerif", fontStyle, fontSize);
        g.setFont(font);

        g.drawString(text, topLeft.x + stroke, topLeft.y + height - fontSize/2 - stroke);

    }

    void paintNormal(Graphics g){

        g.setColor(stroke_color);
        g.fillRect(topLeft.x - stroke , topLeft.y - stroke, width + stroke, height + stroke);

        g.setColor(cell_color);
        g.fillRect(topLeft.x , topLeft.y, width - stroke, height - stroke);

        g.setColor(fontColor);
        font = new Font("SansSerif", fontStyle, fontSize);
        g.setFont(font);

        g.drawString(text, topLeft.x + stroke, topLeft.y + height - fontSize/2);

    }

    void paintHighlighted(Graphics g){

        g.setColor(stroke_color);
        g.fillRect(topLeft.x - stroke , topLeft.y - stroke, width + stroke, height + stroke);

        g.setColor(cell_color);
        g.fillRect(topLeft.x , topLeft.y, width - stroke, height - stroke);

        g.setColor(fontColor);
        font = new Font("SansSerif", fontStyle, fontSize);
        g.setFont(font);

        g.drawString(text, topLeft.x + stroke, topLeft.y + height - fontSize/2);

    }

     /**
      * calculates a fontSize considering the length of the text and the width of the cell
      * @param g
      * @return
      */
    int calculateFontSize(Graphics g){

        font = new Font("SansSerif", fontStyle, fontSize);
        g.setFont(font);

        while (g.getFontMetrics().stringWidth(text) > (width - stroke*3)) {
            fontSize--;
            font = new Font("SansSerif", fontStyle, fontSize);
            g.setFont(font);
        }

        return fontSize;
    }
}
