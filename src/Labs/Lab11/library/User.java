package Labs.Lab11.library;

import java.util.LinkedList;

public class User {

    private String name;
    private String ID;
    private LinkedList<Book> books = new LinkedList<>();

    public User() {}

    public User(String name, String ID) {
        this.name = name;
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Book searchByTitle(String title){

        for (Book b: Library.getInventory()) {
            if (b.getTitle().equals(title))
                return b;
        }

        System.out.println("book not found!");
        return null;

    }

    public LinkedList<Book> searchByAuthor(String author){

        LinkedList<Book> books = new LinkedList<>();
        for (Book b: Library.getInventory()) {
            if (b.getAuthor().equals(author))
                books.add(b);
        }

        if (books.size() == 0)
            System.out.println("no book found!");

        return books;

    }

    public LinkedList<Book> searchBySubject(String subject){

        LinkedList<Book> books = new LinkedList<>();
        for (Book b: Library.getInventory()) {
            if (b.getSubject().equals(subject))
                books.add(b);
        }

        if (books.size() == 0)
            System.out.println("no book found!");

        return books;

    }

    public void checkoutBook(String title){

        if (searchByTitle(title) == null) {
            return;
        }

        Book book = searchByTitle(title);

        if (book.isAvailable()){
            books.add(book);
            book.addCheckoutHistory(name + " " + ID + " checked out!");
            Library.borrowBook(book);
        }


    }

    public void returnBook(Book book){

        book.addCheckoutHistory(name + " " + ID + " returned!");
        if (books.contains(book)){
            books.removeFirstOccurrence(book);
            Library.returnBook(book);
        }

    }

    public void renewBook(Book book){

        book.addCheckoutHistory(name + " " + ID + " renewed!");

    }

    public void viewAvailability(String title){

        if (searchByTitle(title) == null) {
            return;
        }

        Book book = searchByTitle(title);

        if (book.isAvailable())
            System.out.println(book.getTitle() + " is available!");
        System.out.println(book.getTitle() + " is not available!");

    }

    public void viewLocation(String title){

        if (searchByTitle(title) == null) {
            return;
        }

        Book book = searchByTitle(title);

        System.out.println(book.getTitle() + " is located at " + book.getLocation());

    }

    public void viewCheckoutHistory(String title){

        if (searchByTitle(title) == null) {
            return;
        }

        Book book = searchByTitle(title);

        System.out.println(book.getTitle() + "'s checkout history:\n" + book.getCheckoutHistory());

    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", ID='" + ID + '\'' +
                '}';
    }

    public LinkedList<Book> getBooks() {
        return books;
    }
}
