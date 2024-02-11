package Labs.Lab09;

public class SquishyAnt extends Ant{

    public SquishyAnt(int x, int y, int size) {
        super(x, y, size);
    }

    @Override
    public void slide(int x, int y) {

    }

    @Override
    public void walk() {
        y -= 3;
    }
}
