package Labs.Lab14.problem3;

public class Car implements Vehicle{

    private float speed;
    private Operator operator;

    public Car(float speed) {
        this.speed = speed;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    @Override
    public void start() {
        System.out.println("car has started!");
    }

    @Override
    public boolean stop(int distance) {

        if (distance*0.1 / speed > 2.0)
            return true;

        return false;

    }

    @Override
    public String toString() {
        return "Car{" +
                "speed=" + speed +
                ", operator=" + operator +
                '}';
    }
}
