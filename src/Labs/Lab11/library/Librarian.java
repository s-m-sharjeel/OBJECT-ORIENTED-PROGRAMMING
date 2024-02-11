package Labs.Lab11.library;

public class Librarian extends User{

    public Librarian(String name, String ID) {
        super(name, ID);
    }

    public void addBook(Book book){

        Library.getInventory().add(book);

    }


    public void removeBook(Book book){

        Library.getInventory().removeFirstOccurrence(book);

    }

    public void addUser(User user){

        Library.getUsers().add(user);

    }


    public void removeUser(User user){

        Library.getUsers().removeFirstOccurrence(user);

    }

    public void updateBookInfo(Book book, boolean available, String location){
        book.setAvailable(available);
        book.setLocation(location);
    }

    public void manageUserAcc(User user, String name, String ID){
        user.setName(name);
        user.setID(ID);
    }

}
