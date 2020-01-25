package version_4.library;


import java.util.Arrays;

public class Library {

    private Book [] books = new Book[0];

    public Library() {
    }

    public Book[] getBooks() {
        return books;
    }

    public void addBook (Book book){
        books = Arrays.copyOf(books,books.length+1);
        books[books.length-1]=book;
    }

    public void setBooks(Book book, int indexOfBook) {
        this.books[indexOfBook] = book;
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }

    public Book getBooks(int indexOfBook) {
        return books[indexOfBook];
    }

    public Book getBooks(Book book, int indexOfBook) {
        return books[indexOfBook];
    }
}
