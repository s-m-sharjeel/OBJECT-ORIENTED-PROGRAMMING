package Labs.Lab10;

import java.awt.*;

public class City {

    private final String name;
    private final float lat;
    private final float lng;
    private final String country;
    private final String iso2;
    private final String admin_name;
    private final int x;
    private final int y;
    private final int size = 4;
    private Color color;
    private boolean pressed;
    private boolean hovered;

    public City(String name, float lat, float lng, String country, String iso2, String admin_name) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.x = (Math.round(lng*40)) - 2400;
        this.y = Toolkit.getDefaultToolkit().getScreenSize().height + 1000 - (Math.round(lat*50));
        this.country = country;
        this.iso2 = iso2;
        this.admin_name = admin_name;
        switch (admin_name) {
            case "Sindh" -> this.color = Color.red;
            case "Punjab" -> this.color = Color.green;
            case "Khyber Pakhtunkhwa" -> this.color = Color.pink;
            case "Balochistan" -> this.color = Color.orange;
            case "Azad Kashmir" -> this.color = Color.cyan;
            case "Gilgit-Baltistan" -> this.color = Color.blue;
        }
    }

    public void draw(Graphics g){

        g.setColor(color);
        g.drawOval(x - size, y - size, size*2, size*2);

        if (hovered) {
            g.setColor(Color.lightGray);
            g.fillOval(x - size, y - size, size*2, size*2);
            drawToolTip(g);
        }

        if (pressed) {
            g.setColor(Color.gray);
            g.fillOval(x - size, y - size, size*2, size*2);
        }

    }

    public void isClicked(int x, int y){

        // euclidean distance
        if ((x - this.x)*(x - this.x) + (y - this.y)*(y - this.y) < size*size)
            pressed = !pressed;

    }

    public void isHovered(int x, int y){

        // euclidean distance
        hovered = (x - this.x) * (x - this.x) + (y - this.y) * (y - this.y) < size * size;

    }

    private void drawToolTip(Graphics g){

        g.setColor(Color.black);
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 10));
        FontMetrics m = g.getFontMetrics();
        int stringWidth = m.stringWidth(name);
        int stringHeight = m.getAscent() - m.getDescent();

        g.setColor(Color.white);
        g.fillRect(x, y, stringWidth + 6, stringHeight + 6);

        g.setColor(Color.black);
        g.drawRect(x, y, stringWidth + 6, stringHeight + 6);

        g.setColor(Color.black);
        g.drawString(name, x + 3, y + stringHeight + 3);

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }

    @Override
    public String toString() {
        return name;
    }
}
