package Labs.Lab07.animals;

public class Wolf extends Carnivore{

    public Wolf() {
        super("Wolf", 0);
    }

    @Override
    public void eat(Animal animal) {
        if (animal instanceof Herbivore)
            super.eat(animal);
        else System.out.println("Lion cannot eat carnivore!");
    }
}
