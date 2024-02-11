package Labs.Lab07.animals;

public abstract class Animal {

    String name;
    int age;
    boolean alive = true;

    public static class Queue{

        public static Node head;
        public static Node tail;
        public static int length = 0;

        public static boolean isEmpty() {
            return head == null;
        }

        public static void enqueue(Animal animal) {
            Node newNode = new Node(animal);
            if (isEmpty()) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
            length++;
        }

        public static Animal dequeue() {
            if (isEmpty()) {
                System.out.println("Queue is empty");
                return null;
            }
            Animal temp = head.animal;
            head = head.next;
            if (isEmpty()) {
                tail = null;
            }
            length--;
            return temp;
        }

        public static int getLength(){
            return length;
        }
    }

    public abstract void eat(Animal animal);

    public void sleep(){
        if(alive)
            System.out.println(name + " sleeps!");
        else dead();
    }

    public void grow(){
        age++;
    }

    public void die(){
        if(alive){
            System.out.println(name + " dies!");
            alive = false;
        } else dead();

    }

    public void dead(){
        System.out.println("It is dead!");
    }

    public abstract Animal reproduce();

    public static Animal random(Animal animal1, Animal animal2){

        int random = (int)(Math.random()*101);
        if (random <= 50)
            return animal1;
        else return animal2;

    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isAlive() {
        return alive;
    }

}
