package Labs.Lab14.problem1;

public class Dog extends Animal{

    public Dog(String name, String sound) {
        super(name, sound);
    }

    @Override
    public void makeSound() {

        System.out.println("dog woof");

    }
}
