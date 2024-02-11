package Labs.Lab14.problem1;

public class Cat extends Animal{

    public Cat(String name, String sound) {
        super(name, sound);
    }

    @Override
    public void makeSound() {
        System.out.println("cat meow");
    }
}
