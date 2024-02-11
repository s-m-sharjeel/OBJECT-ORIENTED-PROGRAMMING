package Labs.Lab08.problem2;

public class Sedan extends Car implements Convertible{

    @Override
    public void accelerate(int speed) {
        this.speed = speed;
    }

    @Override
    public void decelerate(int speed) {
        this.speed = speed;
    }

    @Override
    public void openRoof() {
        openRoof = true;
    }
}
