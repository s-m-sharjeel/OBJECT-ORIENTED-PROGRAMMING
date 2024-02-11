package Labs.Lab14.problem1;

public class Animal {

    private final String name;
    private final String sound;

    public Animal(String name, String sound) {
        this.name = name;
        this.sound = sound;
    }

    public void makeSound(){

        System.out.println(name + " " + sound);

    }

}
