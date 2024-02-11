package Labs.Lab07.animals;

public class Herbivore extends Animal{

    Herbivore(String name, int age){
        this.name = name;
        this.age = age;
    }

    @Override
    public void eat(Animal animal) {
        if(this.isAlive())
            System.out.println(name + " eats plants.");
        else this.die();
    }

    @Override
    public Animal reproduce() {
        System.out.println(this.name + " reproduces!");
        return new Herbivore(this.name, 0);
    }

}
