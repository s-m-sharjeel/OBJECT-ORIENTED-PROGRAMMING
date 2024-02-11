package Labs.Lab09;

public class SlipperyAnt extends Ant{

    public SlipperyAnt(int x, int y, int size) {
        super(x, y, size);
    }

    @Override
    public void slide(int x, int y) {

        if (((x - this.x)*(x - this.x)) + ((y - this.y)*(y - this.y)) <= (this.size*this.size)) {

            if (x > this.x)
                this.x--;
            else if (x < this.x)
                this.x++;

            if (y > this.y)
                this.y--;
            else if (y < this.y)
                this.y++;

        }

    }

    @Override
    public void walk() {

    }

}
