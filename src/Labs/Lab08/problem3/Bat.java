package Labs.Lab08.problem3;

public class Bat implements Animal, Flyer{

    @Override
    public void flies() {
        System.out.println("bat flies");
    }

    @Override
    public void say() {
        System.out.println("I am a Bat");
    }
}
