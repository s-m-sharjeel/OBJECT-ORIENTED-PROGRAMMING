package Labs.Lab10;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class Map extends JPanel implements ActionListener , MouseInputListener {

    private final int B_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width/2;
    private final int B_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

    private Timer timer;
    private final int DELAY = 25;

    private LinkedList<City> pakistan;
    private City fromCity;
    private City toCity;

    public Map(LinkedList<City> pakistan) {

        this.pakistan = pakistan;
        initMap();
    }

    private void InitializeAssets() {


    }

    private void initMap() {

    	addMouseListener( this );
    	addMouseMotionListener( this );
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        setFocusable(true);

        InitializeAssets();
        
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.black);
        g.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
        String str = "OHIO";
        g.drawString(str, B_WIDTH/2 - g.getFontMetrics().stringWidth(str)/2, 60);

        g.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        str = "From:";
        if (fromCity != null)
            str += " " + fromCity;
        g.drawString(str, B_WIDTH/2 - g.getFontMetrics().stringWidth(str)/2, 100);

        g.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        str = "To:";
        if (toCity != null)
            str += " " + toCity;
        g.drawString(str, B_WIDTH/2 - g.getFontMetrics().stringWidth(str)/2, 130);

        drawCities(g);
        drawLine(g);


    }

    private void drawCities(Graphics g) {

        for (City c : pakistan) {
            c.draw(g);
        }

    }

    public void drawLine(Graphics g){

        if (fromCity == null || toCity == null)
            return;

        int fromX = fromCity.getX();
        int fromY = fromCity.getY();
        int toX = toCity.getX();
        int toY = toCity.getY();

        double lng = (Math.abs(fromCity.getLng() - toCity.getLng())*111.321);

        double lat = (Math.abs(fromCity.getLat() - toCity.getLat())*68.703);

        String distance =  "" + Math.sqrt((lng*lng + lat*lat));
        String[] temp = distance.split("\\.");
        distance = temp[0] + "." + temp[1].substring(0, 3) + " km";

        g.setColor(Color.red);
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        FontMetrics m = g.getFontMetrics();
        int stringWidth = m.stringWidth(distance);
        int stringHeight = m.getAscent() - m.getDescent();

        g.drawString(distance, (fromX + toX)/2 - stringWidth/2, (fromY + toY)/2 + stringHeight/2);

        g.setColor(Color.gray);
        g.drawLine(fromX, fromY, toX, toY);

    }

    @Override
    public void mousePressed(MouseEvent e) {

        int x = e.getX();
        int y = e.getY();

        if (fromCity != null && toCity != null){
            for (City city : pakistan) {
                city.setPressed(false);
            }
            fromCity = null;
            toCity = null;
        }

        if (fromCity == null){
            for (City city : pakistan) {
                city.isClicked(x, y);
                if (city.isPressed()) {
                    fromCity = city;
                    break;
                }
            }

        } else {
            for (City city : pakistan) {
                city.isClicked(x, y);
                if (city.isPressed() && !city.equals(fromCity)) {
                    toCity = city;
                    break;
                }
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Toolkit.getDefaultToolkit().sync();
        repaint();
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub


	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseMoved(MouseEvent e) {

        for (City c : pakistan) {
            c.isHovered(e.getX(), e.getY());
        }

	}
}