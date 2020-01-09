package HomeLibrary.library;

import java.util.Objects;

public class Book {
    private String bookName;
    private String author;
    private String typeOfBook;
    private String aboutBook;

    public Book() {
    }

    public Book(String bookName, String author, String typeOfBook, String aboutBook) {
        this.bookName = bookName;
        this.author = author;
        this.typeOfBook = typeOfBook;
        this.aboutBook = aboutBook;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTypeOfBook() {
        return typeOfBook;
    }

    public void setTypeOfBook(String typeOfBook) {
        this.typeOfBook = typeOfBook;
    }

    public String getAboutBook() {
        return aboutBook;
    }

    public void setAboutBook(String aboutBook) {
        this.aboutBook = aboutBook;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(bookName, book.bookName) &&
                Objects.equals(author, book.author) &&
                Objects.equals(typeOfBook, book.typeOfBook) &&
                Objects.equals(aboutBook, book.aboutBook);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookName, author, typeOfBook, aboutBook);
    }

    @Override
    public String toString() {
        return "Book - " + bookName + '\'' +
                ", Author - " + author + '\'' +
                ", Type of book - " + typeOfBook + '\'' +
                ", About - " + aboutBook + '\'' +
                '}';
    }
}
