package Labs.Lab14.problem3;

public class Helicopter implements Vehicle{

    private float rotations;
    private Pilot pilot;

    public Helicopter(float rotations) {
        this.rotations = rotations;
    }

    public void setOperator(Pilot pilot) {
        this.pilot = pilot;
    }

    @Override
    public void start() {
        System.out.println("helicopter has started!");
    }

    @Override
    public boolean stop(int distance) {
        if (distance*0.1 / rotations > 2.0)
            return true;

        return false;
    }

    @Override
    public String toString() {
        return "Helicopter{" +
                "rotations=" + rotations +
                ", pilot=" + pilot +
                '}';
    }
}
