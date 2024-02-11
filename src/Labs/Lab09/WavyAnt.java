package Labs.Lab09;

public class WavyAnt extends Ant{

    int axisX;
    int wavelength;

    public WavyAnt(int x, int y, int size) {
        super(x, y, size);
        axisX = x;
        wavelength = 2 + (int)(Math.random()*5);    // assigns a random wavelength between 2 and 5
    }

    @Override
    public void slide(int x, int y) {

    }

    @Override
    public void walk() {
        y -= wavelength;
        x = axisX + (int)(10*Math.sin((y*0.1)));
    }
}
