package Labs.Lab11.project;

public class Programmer extends Employee{

    public Programmer(String name, String ID) {
        super(name, ID);
    }

    @Override
    protected int work() {
        linesOfCodePerDay = 500 + (int)(Math.random()*1001);
        return linesOfCodePerDay;
    }

    @Override
    protected float salary() {
        work();
        float totalHrs = (time/60)*(linesOfCodePerDay/24);
        return rate*totalHrs;
    }

    @Override
    public String toString() {
        return "Programmer{" + super.toString();
    }
}
