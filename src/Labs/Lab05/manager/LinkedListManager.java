package Labs.Lab05.manager;
import Labs.Lab05.list.LinkedList;

public class LinkedListManager {

    public static void main ( String [] args ) {
        LinkedList l = new LinkedList() ;
        l.append(19);
        l.append(19);
        l.append(19);
        l.append(19);
        l.append(19);
        l.append(19);
        l.removeAll(19);
        l.displayList();
        l.ASCII("Sharjeel");

        l.displayList();
        System.out.println(l.getString());
        LinkedList s = new LinkedList() ;
        s.append('r');
        s.append('e');
        s.append('o');
        s.append('g');
        s.append('s');
        s.append('y');
        s.append('g');
        s.append('g');
        s.append('g');
        s.append('g');
        s.add('L', 9);
        System.out.println(s.getString());
        s.removeAll('g');
        System.out.println(s.getString());
    }

}
