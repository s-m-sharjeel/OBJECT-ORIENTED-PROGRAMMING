package Labs.Lab08.problem3;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args){

        Animal bat = new Bat();
        Animal chimera = new Chimera();
        Animal platypus = new Platypus();

        LinkedList<Animal> animals = new LinkedList<>();

        animals.add(bat);
        animals.add(chimera);
        animals.add(platypus);

        for (Animal animal : animals) {

            animal.say();

            if (animal instanceof Bat) {
                ((Bat) animal).flies();
            } else if (animal instanceof Chimera){
                ((Chimera) animal).flies();
                ((Chimera) animal).givesMilk();
                ((Chimera) animal).laysEggs();
            } else if (animal instanceof Platypus){
                ((Platypus) animal).laysEggs();
                ((Platypus) animal).givesMilk();
            }

        }
    }

}
