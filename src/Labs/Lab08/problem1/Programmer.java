package Labs.Lab08.problem1;

public class Programmer extends Employee{

    @Override
    public void calculateBonus() {
        // 10% bonus
        bonus = salary/10;
    }

    @Override
    public void promote(double newSalary) {
        salary = newSalary;
    }
}
