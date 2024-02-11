package Labs.Lab06.vehicles;

public class Stack {

    Node head;
    int length;

    private class Node {
        Car car;
        Node next;

        public Node(Car car) {

            this.car = car;
            this.next = null;

        }
    }

    public void load(Car car) {
        if (head == null) {
            head = new Node(car);
            length++;
            return;
        }
        Node temp = new Node(car);
        temp.next = head;
        head = temp;
        length++;
    }

    public Car unload(){
        if (head == null) {
            return null;
        }
        Car temp = head.car;
        head = head.next;
        return temp;
    }

    public int getLength(){
        return length;
    }

}
