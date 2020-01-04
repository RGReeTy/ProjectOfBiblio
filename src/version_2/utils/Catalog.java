package version_2.utils;


import version_2.entity.book.Book;
import version_2.entity.user.Email;
import version_2.entity.user.User;

import java.util.List;

/**
 * Catalog class is a singleton.
 */
public class Catalog {

    private static Catalog INSTANCE;

    private List<Book> books;

    private User user;
    private Email systemEmail;

    private Catalog(String username, String password) {
        //this.user = UserUtils.login(username, password);
        this.books = CatalogFileHandler.loadBooks();
        this.systemEmail = new Email("catalog@mail.by");
    }

    public static Catalog getCatalog(String username, String password) {
        if (INSTANCE == null) {
            INSTANCE = new Catalog(username, password);
        }
        return INSTANCE;
    }

    public User getUser() {
        return user;
    }

//    public void setUser(User user) {
//        this.user = UserUtils.login(user.getUsername(), new String(user.getPassword()));
//    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Email getSystemEmail() {
        return systemEmail;
    }

    public void setSystemEmail(Email systemEmail) {
        this.systemEmail = systemEmail;
    }


}
