package Labs.Lab07.manager;

import Labs.Lab07.animals.*;

public class AnimalManager {

    public static void main (String[] args) {

        Animal.Queue.enqueue(Animal.random(new Lion(), new Wolf()));

        int noOfAnimals = 3;

        while(Animal.Queue.getLength() < noOfAnimals){

            Animal.Queue.enqueue(Animal.random(new Cow(), new Goat()));

        }

        int random;
        int eaten = 0;
        Animal carnivore = Animal.Queue.dequeue();
        while (Animal.Queue.getLength()>0){

            // carnivore eating
            random = 1 + (int)(Math.random()*100);
            if(random <= 50){
                assert carnivore != null;
                carnivore.eat(Animal.Queue.dequeue());
                // carnivore growing
                carnivore.grow();
                eaten++;
            }

            // herbivores growing
            if(Animal.Queue.head != null) {
                Node current = Animal.Queue.head;
                while (current.next != null) {
                    current.animal.grow();
                    current = current.next;
                }
            }

            // herbivore reproducing
            if(Animal.Queue.head != null) {
                Animal herbivore = Animal.Queue.head.animal;
                random = 1+ (int)(Math.random()*100);
                if(random <= 25){
                    Animal.Queue.enqueue(herbivore.reproduce());
                    Animal.Queue.enqueue(herbivore.reproduce());
                }
            }

        }

        System.out.println(eaten + " herbivores eaten");
        assert carnivore != null;
        System.out.println(carnivore.getName() + "'s age: " + carnivore.getAge());

    }

}
