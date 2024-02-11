package Labs.Lab14.problem2;

public class Employee {

    private String name;
    private float salary;
    private String department;

    public Employee(String name, float salary, String department) {
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", department='" + department + '\'' +
                '}';
    }
}
