package Final_Exam.question3;

public class Motorcycle implements Vehicle{
    @Override
    public void start() {
        System.out.println("Motorcycle starting!");
    }

    @Override
    public void stop() {
        System.out.println("Motorcycle stopping!");
    }
}
