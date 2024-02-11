package Assignments.Assignment2.drawing;
import java.awt.*;

abstract class Shape {

    protected Point point = new Point(0, 0);
    protected Color color;
    protected int size;
    protected int width;
    protected int length;
    protected int[] x = new int[3];
    protected int[] y = new int[3];

    // node contains shape
    static class Node {

        Shape shape;
        Node next;

        Node(Shape shape) {
            this.shape = shape;
            this.next = null;
        }

    }
    // static stack of shapes
    static class Shapes {

        static Node head;
        static int length;

        static void push(Shape shape) {

            if (head == null) {
                head = new Node(shape);
            } else {
                Node temp = new Node(shape);
                temp.next = head;
                head = temp;
            }
            length++;
        }

        static Shape pop() {

            if (head == null)
                return null;
            else {
                Shape temp = head.shape;
                head = head.next;
                length--;
                return temp;
            }
        }
    }
    // static stack for redo function
    static class RedoList {

        static Node head;
        static int length;

        static void push(Shape shape) {

            if (head == null) {
                head = new Node(shape);
            } else {
                Node temp = new Node(shape);
                temp.next = head;
                head = temp;
            }
            length++;
        }

        static Shape pop() {

            if (head == null)
                return null;
            else {
                Shape temp = head.shape;
                head = head.next;
                length--;
                return temp;
            }
        }

        static void purge() {

            if (head == null)
                return;

            head = null;
            length = 0;

        }

    }

    // constructor:
    protected Shape(Color color) {
        this.color = color;
    }

    // abstract draw function
    abstract void draw(Graphics g);

    // setter(s):
    void setPoint(Point point) {this.point = point;}
    void setSize(int size) {this.size = size;}
    void setWidth(int width) {this.width = width;}
    void setLength(int length) {this.length = length;}
    void setX(int[] x) {this.x = x;}
    void setY(int[] y) {this.y = y;}

}

