package Labs.Lab02;

public class Book {
    
    String title;
    String author;
    String ISBN;
    int pages;
    
    public Book(String title, String author, String ISBN, int pages){
        
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.pages = pages;
        
    }
    
    public String summary(){
        
        return "Title: " + title + "\nAuthor: " + author + "\nISBN: " + ISBN + "\nNumber of pages: " + pages;
        
    }
    
    public static void main(String[] args){
        
        Book book = new Book("Harry Potter and the Sorcerer ’s Stone", "J.K. Rowling", "1234567890", 309);
        
        System.out.println(book.summary());
        
    }
    
}
