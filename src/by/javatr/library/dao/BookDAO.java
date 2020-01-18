package by.javatr.library.dao;

import by.javatr.library.bean.Book;
import by.javatr.library.dao.exception.DAOException;

public interface BookDAO {

    void addBook(Book book) throws DAOException;

    void deleteBook(long idBook) throws DAOException;

}
