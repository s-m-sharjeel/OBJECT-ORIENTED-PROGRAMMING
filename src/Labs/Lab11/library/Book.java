package Labs.Lab11.library;

import java.util.LinkedList;

public class Book {

    private String title;
    private String author;
    private String subject;
    private boolean available;
    private String location;
    private LinkedList<String> checkoutHistory;

    public Book(String title, String author, String subject, String location) {
        this.title = title;
        this.author = author;
        this.subject = subject;
        this.location = location;
        available = true;
        checkoutHistory = new LinkedList<>();
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getSubject() {
        return subject;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getLocation() {
        return location;
    }

    public LinkedList<String> getCheckoutHistory() {
        return checkoutHistory;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void addCheckoutHistory(String checkout) {
        this.checkoutHistory.add(checkout);
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                '}';
    }
}
