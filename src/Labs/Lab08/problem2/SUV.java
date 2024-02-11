package Labs.Lab08.problem2;

public class SUV extends Car {

    @Override
    public void accelerate(int speed) {
        this.speed = speed;
    }

    @Override
    public void decelerate(int speed) {
        this.speed = speed;
    }
}
