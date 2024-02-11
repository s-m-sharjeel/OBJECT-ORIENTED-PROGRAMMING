package Labs.Lab14.problem3;

public class Bike implements Vehicle{

    private int gear;
    private Operator operator;

    public Bike(int gear) {
        this.gear = gear;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    @Override
    public void start() {
        System.out.println("bike has started!");
    }

    @Override
    public boolean stop(int distance) {

        if (distance*0.1 / gear > 0.2)
            return true;

        return false;

    }

    @Override
    public String toString() {
        return "Bike{" +
                "gear=" + gear +
                ", operator=" + operator +
                '}';
    }
}
