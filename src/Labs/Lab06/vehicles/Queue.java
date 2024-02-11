package Labs.Lab06.vehicles;

public class Queue {

    private class Node{
        Truck truck;
        Node next;

        Node(Truck truck) {
            this.truck = truck;
            this.next = null;
        }
    }
    public Node head;
    public Node tail;
    public int length = 0;

    public void load(Truck truck) {
        Node newNode = new Node(truck);
        if (head == null && tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        length++;
    }

    /**
     * unloads a single truck (FIFO)
     * @return
     */
    public Truck unloadTruck(){
        if (head == null && tail == null) {
            System.out.println("Truck is empty");
            return null;
        }
        Truck temp = head.truck;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return temp;
    }

    /**
     * unloads all the trucks (FIFO)
     * @return
     */
    public Truck[] unload(){

        Truck[] trucks = new Truck[getLength()];
        for (int i = 0; i < getLength(); i++) {
            trucks[i] = unloadTruck();
        }

        return trucks;

    }

    public int getLength(){
        return length;
    }

}
