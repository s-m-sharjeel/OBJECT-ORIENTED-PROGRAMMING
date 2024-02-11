package Labs.Lab14.problem2;

public class Manager extends Employee{

    private float bonus;

    public Manager(String name, float salary, String department) {
        super(name, salary, department);
    }

    @Override
    public String toString() {
        return super.toString() + '\n' + "Manager{" +
                "bonus=" + bonus +
                '}';
    }
}
