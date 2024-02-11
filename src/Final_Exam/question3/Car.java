package Final_Exam.question3;

public class Car implements Vehicle{

    @Override
    public void start() {
        System.out.println("Car starting!");
    }

    @Override
    public void stop() {
        System.out.println("Car stopping!");
    }
}
