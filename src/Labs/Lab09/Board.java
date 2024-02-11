package Labs.Lab09;

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.MouseInputListener;

public class Board extends JPanel implements ActionListener , MouseInputListener {

    private final int B_WIDTH = 400;
    private final int B_HEIGHT = 400;
    private final int DELAY = 25;

    private Timer timer;

    private int totalAntsSquished = 0;

    private LinkedList<Ant> ants = new LinkedList<>();

    public Board() {

        initBoard();
    }

    private void InitializeAssets() {

        int size = 20;

        ants.add(new SlipperyAnt(200, 200, size));


        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0)
                ants.add(new SquishyAnt(size + (int)(Math.random()*(B_WIDTH - size*2)), B_HEIGHT*2 + (size*2*i), size));
            else ants.add(new WavyAnt(size + (int)(Math.random()*(B_WIDTH - size*2)), B_HEIGHT*2 + (size*2*i), size));
        }

    }

    private void initBoard() {

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

        drawAnts(g);
        antsWalk();

        displayScore(g);

        if (ants.size() == totalAntsSquished){
            System.out.println("Final Score: " + ants.size());  //same as totalAntsSquished
            System.exit(0);
        }

    }

    private void displayScore(Graphics g){

        String s = "Score: " + totalAntsSquished;

        g.setColor(Color.black);
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));

        FontMetrics m = g.getFontMetrics();
        int stringWidth = m.stringWidth(s);
        int stringHeight = m.getAscent() - m.getDescent();

        g.drawString(s, B_WIDTH/2 - stringWidth/2, 15 + stringHeight/2);

    }

    private void drawAnts(Graphics g) {

        for (int i = 0; i < ants.size(); i++) {
            if (hasEscaped(ants.get(i)))
                ants.remove(i);
            else g.drawImage(ants.get(i).getImage(), ants.get(i).x - ants.get(i).size, ants.get(i).y - ants.get(i).size, this);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Toolkit.getDefaultToolkit().sync();
        repaint();
    }

    public void antsWalk(){
        for (Ant ant : ants) {
            if (!ant.isSquished())
                ant.walk();
        }
    }

    public void IsClicked(int x, int y) {
        for (Ant ant : ants) {
            if (!ant.isSquished() && !(ant instanceof SlipperyAnt)) {
                ant.isClicked(x, y);
                if (ant.isSquished())
                    totalAntsSquished++;
            }
        }

    }
    

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        IsClicked(e.getX(), e.getY());
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

        int x = e.getX();
        int y = e.getY();

        for (Ant ant : ants) {
            if (ant instanceof SlipperyAnt) {
                if (!ant.isSquished())
                    ant.slide(x, y);
            }
        }

	}

    private boolean hasEscaped(Ant ant){
        if (ant.y < -40)
            return true;

        return false;
    }
}