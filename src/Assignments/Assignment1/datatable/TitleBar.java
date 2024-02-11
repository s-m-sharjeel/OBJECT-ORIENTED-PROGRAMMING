package Assignments.Assignment1.datatable;

import java.awt.*;

public class TitleBar extends Cell{

    private int button_size;
    private int gap;
    private int buttonStroke = 1;
    TitleBar(int x, int y, int width, int height, Color cell_color, Color stroke_color, Color fontColor, int stroke, String text) {
        super(x, y, width, height, cell_color, stroke_color, fontColor, stroke, text);
        fontStyle = Font.BOLD;
        button_size = height/2;
        gap = height/4;
    }

    /**
     * Paints a TitleBar with bold text.
     * @param g
     */
    void paint(Graphics g) {

        font = new Font("SansSerif", fontStyle, fontSize);
        g.setFont(font);

        while (g.getFontMetrics().stringWidth(text) > (width - button_size - gap*2)) {
            fontSize--;
            font = new Font("SansSerif", fontStyle, fontSize);
            g.setFont(font);
        }

        super.paint(g);

        paintButton(g);

    }

    /**
     * paints a red (non-functional) button
     * @param g
     */
    private void paintButton(Graphics g){

        g.setColor(Color.GRAY);
        g.fillRect(topLeft.x + width - button_size - buttonStroke - gap - stroke, topLeft.y - buttonStroke + gap, button_size + (2*buttonStroke),  button_size + (2*buttonStroke));

        g.setColor(Color.RED);
        g.fillRect(topLeft.x + width - button_size - gap - stroke, topLeft.y + gap, button_size, button_size);

    }

}
