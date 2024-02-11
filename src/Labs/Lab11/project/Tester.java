package Labs.Lab11.project;

public class Tester extends Employee{

    public Tester(String name, String ID) {
        super(name, ID);
    }

    @Override
    protected int work() {
        linesOfCodePerDay = 150 + (int)(Math.random()*101);
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
        return "Tester{" + super.toString();
    }
}
