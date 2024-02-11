package Labs.Lab05.list;

public class LinkedList {
    Node head;

    /**
     * @param data is added to the list
     */
    public void append(int data) {
        if (head == null) {
            head = new Node(data);
            return;
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = new Node(data);
    }

    /**
     * @param data's first occurance is removed from list
     */
    public void remove(int data) {
        if (head == null) {
            return;
        }
        if (head.data == data) {
            head = head.next;
            System.out.println("removed");
            return;
        }
        Node current = head;
        while(current.next != null){
            if(current.next.data == data){
                current.next = current.next.next;
                return;
            }
            current = current.next;
        }

    }

    /**
     * @param data is added to list at
     * @param index
     */
    public void add(int data, int index){

        if (head == null) {
            head = new Node(data);
            return;
        }

        if(index == 0){
            head = new Node(data, head);
            return;
        }

        Node current = head;
        int j = 0;
        while ((current.next != null) && (j < index-1)) {
            current = current.next;
            j++;
        }

        if(current.next == null){
            current.next = new Node(data);
        }else{
            Node temp = current.next;
            current.next = new Node(data, temp);
        }
    }

    /**
     * data at index is removed
     * @param index
     */
    public void removeFromIndex(int index){

        if (head == null) {
            return;
        }

        if(index == 0){
            head = head.next;
            return;
        }

        if(index == getLength()-1){
            int j = 0;
            Node temp = head;
            while(j < index-1){
                temp = temp.next;
                j++;
            }
            temp.next = null;
            return;
        }

        if (index > getLength()-1){
            System.out.println("Index out of bounds");
            return;
        }

        Node current = head;

        int j = 0;
        while (j < index - 1) {
            current = current.next;
            j++;
        }
        current.next = current.next.next;
    }

    /**
     * removes all occurrences of data from the list
     * @param data
     */
    public void removeAll(int data){

        for (int i = getLength() - 1; i >= 0 ; i--) {
            if (getData(i) == data){
                removeFromIndex(i);
            }
        }

    }

    /**
     * @param index
     * @return data at index
     */
    public int getData(int index){

        if(head == null){
            return -1;
        }

        if (index == 0)
            return head.data;

        if (index > getLength()-1)
            return -1;

        Node current = head.next;
        int j = 1;
        while (j<index) {
            current = current.next;
            j++;
        }
        return current.data;

    }

    /**
     * @return the length of the list
     */
    public int getLength(){

        if (head == null) {
            return -1;
        }
        Node current = head;
        int j = 1;
        while (current.next != null) {
            current = current.next;
            j++;
        }
        return j;

    }

    /**
     * displays the current state of the list with all the number entries
     */
    public void displayList(){

        if(head == null)
            return;

        String str = "[";
        for (int i = 0; i < getLength() - 1; i++) {
            str += " " + getData(i) + ",";
        }
        str += " " + getData(getLength()-1) + " ]";
        System.out.println(str);
    }

    /**
     * creates a list with the ASCII values of the input string
     * @param s
     */
    public void ASCII(String s){
        for (int i = 0; i < s.length(); i++) {
            append(s.charAt(i));
        }
    }

    /**
     * @return a string by retrieving all the ASCII values of the stored characters
     */
    public String getString(){
        String s = "";
        for (int i = 0; i < getLength(); i++) {
            s += (char)(getData(i));
        }
        return s;
    }

}