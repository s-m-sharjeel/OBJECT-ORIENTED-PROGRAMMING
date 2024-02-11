package Labs.Lab07.animals;

public class Node{
    public Animal animal;
    public Node next;

    public Node(Animal animal) {
        this.animal = animal;
        this.next = null;
    }
}
