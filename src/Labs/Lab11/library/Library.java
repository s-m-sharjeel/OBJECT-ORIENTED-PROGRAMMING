package Labs.Lab11.library;

import java.util.LinkedList;

public class Library {

    private static LinkedList<Book> inventory = new LinkedList<>();
    private static LinkedList<User> users = new LinkedList<>();
    private static LinkedList<Librarian> librarians = new LinkedList<>();


    public static LinkedList<Book> getInventory() {
        return inventory;
    }

    public static LinkedList<User> getUsers() {
        return users;
    }

    public static void borrowBook(Book book){
        book.setAvailable(false);
    }

    public static void returnBook(Book book){
        book.setAvailable(true);
    }

    public static void generateReport(){

        System.out.println("popular books:");
        System.out.println(getPopularBooks());

        System.out.println("popular category:");
        System.out.println(getPopularCategory());

        System.out.println("overdue books:");
        System.out.println(getOverDueBooks());


    }

    private static LinkedList<Book> getPopularBooks(){

        int popular = 0;
        for (Book b : inventory) {
            if (b.getCheckoutHistory().size() > popular){
                popular = b.getCheckoutHistory().size();
            }
        }

        LinkedList<Book> popularBooks = new LinkedList<>();
        for (Book b : inventory) {
            if (b.getCheckoutHistory().size() == popular)
                if (!popularBooks.contains(b))
                    popularBooks.add(b);
        }

        return popularBooks;

    }

    private static LinkedList<Book> getOverDueBooks(){

        LinkedList<Book> overDueBooks = new LinkedList<>();

        // overdue books when more than 3 are borrowed
        for (User user : users) {

            if (user.getBooks().size() > 2){

                for (int i = 0; i < user.getBooks().size() - 2; i++) {
                    overDueBooks.add(user.getBooks().get(i));
                }

            }

        }

        return overDueBooks;

    }

    private static LinkedList<String> getPopularCategory(){

        // all categories of books borrowed
        LinkedList<String> categories = new LinkedList<>();
        for (Book b : inventory) {
            if (!b.isAvailable()){
                categories.add(b.getSubject());
            }
        }

        // index of most popular category
        int popular = 0;
        for (int i = 0; i < categories.size(); i++) {
            int k = 0;
            for (int j = 0; j < categories.size(); j++) {
                if (i != j){
                    if (categories.get(i).equals(categories.get(j)))
                        k++;
                }
            }
            if (k > popular)
                popular = i;
        }

        // list of all popular categories
        LinkedList<String> popularCategories = new LinkedList<>();
        for (int i = 0; i < categories.size(); i++) {
            int k = 0;
            for (int j = 0; j < categories.size(); j++) {
                if (i != j){
                    if (categories.get(i).equals(categories.get(j)))
                        k++;
                }
            }
            if (k == popular && !popularCategories.contains(categories.get(i)))
                popularCategories.add(categories.get(i));
        }

        return popularCategories;

    }

    public static void addLibrarian(Librarian lib){
        librarians.add(lib);
    }

}
