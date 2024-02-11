package Labs.Lab07.animals;

public class Carnivore extends Animal{

    public Carnivore(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void eat(Animal animal) {

        if(this.isAlive())
            System.out.println(this.name + " eats " + animal.name + "!");
        else this.die();

    }

    @Override
    public Animal reproduce() {
        System.out.println(this.name + " reproduces!");
        return new Carnivore(this.name, 0);
    }

}
