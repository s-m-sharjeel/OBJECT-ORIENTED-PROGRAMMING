package Labs.Lab08.problem1;

public abstract class Employee implements Promotable{

    String name;
    double salary;
    double bonus;

    public abstract void calculateBonus();

}
