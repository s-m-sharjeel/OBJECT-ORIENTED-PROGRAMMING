package Labs.Lab11.library;

public class LibraryManager {

    public static void main(String[] args) {

        Librarian lib = new Librarian("xyz", "123");
        Library.addLibrarian(lib);

        User user = new User("Sharjeel", "26932");

        lib.addUser(user);
        Book book1 = new Book("hobbit1", "jrr tolkiens", "sci-fi", "right");
        Book book2 = new Book("hobbit2", "jrr tolkiens", "sci-fi", "right");
        Book book3 = new Book("hobbit3", "jrr tolkiens", "sci-fi", "right");
        Book book4 = new Book("hobbit4", "jrr tolkiens", "sci-fi", "right");

        lib.addBook(book1);
        lib.addBook(book2);
        lib.addBook(book3);
        lib.addBook(book4);

        user.checkoutBook("hobbit1");
        user.checkoutBook("hobbit2");
        user.checkoutBook("hobbit3");
        user.checkoutBook("hobbit4");
        user.returnBook(book4);
        user.checkoutBook("hobbit4");

        Library.generateReport();

    }

}
