package Labs.Lab09;

import javax.swing.*;
import java.awt.Image;

public abstract class Ant {

	public int x;
	public int y;
	public int size;
	private boolean squished;
	private Image[] images = new Image[24];
	int state;
	
	public Ant(int x, int y, int size) {

		this.x = x;
		this.y = y;
		this.size = size;
		for (int i = 0; i < 24; i++) {
			images[i] = new ImageIcon("./src/Labs/Lab09/ant/tile" + (i+1) + ".png").getImage().getScaledInstance(size*2, size*2, Image.SCALE_SMOOTH);;
		}
	}	
		
	public Image getImage() {

		if (squished)
			return images[23];
		else {
			Image img = images[state%22];
			state++;
			return img;
		}
	}
	
	public Boolean isSquished() {
		return squished;
	}

	public abstract void slide(int x, int y);

	public abstract void walk();
	
	public boolean isClicked(int x, int y) {

		// euclidean distance
		if ((x - this.x)*(x - this.x) + (y - this.y)*(y - this.y) < size*size)
		{
			squished = true;
			return true;
		}
		return false;
	}
}
