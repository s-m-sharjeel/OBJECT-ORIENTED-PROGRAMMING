package Labs.Lab08.problem3;

public class Chimera implements Animal, WaterBreather, EggLayer, MilkProvider, Flyer{

    @Override
    public void laysEggs() {
        System.out.println("chimera lays eggs");
    }

    @Override
    public void flies() {
        System.out.println("chimera flies");
    }

    @Override
    public void givesMilk() {
        System.out.println("chimera gives milk");
    }

    @Override
    public void waterBreathing() {
        System.out.println("chimera breathes water");
    }

    @Override
    public void say() {
        System.out.println("I am a Chimera");
    }
}
