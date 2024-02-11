package Labs.Lab08.problem1;

public class Manager extends Employee{

    @Override
    public void calculateBonus() {
        // 20% bonus
        bonus = salary/5;
    }

    @Override
    public void promote(double newSalary) {
        salary = newSalary;
    }
}
