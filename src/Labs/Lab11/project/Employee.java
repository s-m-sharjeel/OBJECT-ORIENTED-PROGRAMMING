package Labs.Lab11.project;

public abstract class Employee {

    private String name;
    private String ID;
    protected float time;
    protected float rate;
    protected int linesOfCodePerDay;

    protected Employee(String name, String ID) {
        this.name = name;
        this.ID = ID;
    }

    protected abstract int work();

    protected abstract float salary();

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", ID='" + ID + '\'' +
                '}';
    }
}
