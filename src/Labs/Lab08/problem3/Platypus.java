package Labs.Lab08.problem3;

public class Platypus extends Mammal implements Animal, EggLayer{
    @Override
    public void laysEggs() {
        System.out.println("platypus lays eggs");
    }

    @Override
    public void givesMilk() {
        System.out.println("platypus gives milk");
    }

    @Override
    public void say() {
        System.out.println("I am a Platypus");
    }
}
