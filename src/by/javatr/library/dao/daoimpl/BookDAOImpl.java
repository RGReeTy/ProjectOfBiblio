package by.javatr.library.dao.daoimpl;

import by.javatr.library.bean.Book;
import by.javatr.library.dao.BookDAO;
import by.javatr.library.dao.exception.DAOException;

import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {

    private List<Book> books;

    public BookDAOImpl() {
        books = new ArrayList<>();
        //books.add(вызов метода для заполнения книгами
    }

    @Override
    public List<Book> getAllBooks() {
        return books;
    }

    @Override
    public void addBook(Book book) throws DAOException {
        books.add(book);
    }

    @Override
    public void deleteBook(int idBook) throws DAOException {
        books.remove(books.get(idBook));
    }
}
