package version_3;

public class BookSuggest {

    private Book book;
    private User user;

    public BookSuggest(Book book, User user) {
        this.book = book;
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public User getUser() {
        return user;
    }
}
