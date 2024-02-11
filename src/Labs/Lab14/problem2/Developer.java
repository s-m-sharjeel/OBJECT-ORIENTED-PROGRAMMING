package Labs.Lab14.problem2;

public class Developer extends Employee{

    private String language;

    public Developer(String name, float salary, String department) {
        super(name, salary, department);
    }

    @Override
    public String toString() {
        return super.toString() + '\n' + "Developer{" +
                "language='" + language + '\'' +
                '}';
    }
}
