package Labs.Lab07.animals;

public class Lion extends Carnivore{

    public Lion() {
        super("Lion", 0);
    }

    @Override
    public void eat(Animal animal) {
        if (animal instanceof Herbivore)
            super.eat(animal);
        else System.out.println("Lion cannot eat carnivore!");
    }
}
