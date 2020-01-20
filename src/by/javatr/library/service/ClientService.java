package by.javatr.library.service;

import by.javatr.library.bean.Book;
import by.javatr.library.bean.User;
import by.javatr.library.dao.daoimpl.BookDAOImpl;
import by.javatr.library.dao.daoimpl.UserDAOImpl;
import by.javatr.library.dao.exception.DAOException;
import by.javatr.library.service.exception.ServiceException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ClientService {
    UserDAOImpl userDAO = new UserDAOImpl();
    BookDAOImpl bookDAO = new BookDAOImpl();
    private User currentUser;
    private ArrayList<Book> books;

    public ClientService() throws FileNotFoundException, DAOException {
        currentUser = userDAO.getCurrentUser();
    }

    public boolean signIn(String login, String password) throws ServiceException {
        try {
            return userDAO.signIn(login, password);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Book> returnCollectionOfBooks() {
        return books = bookDAO.getAllBooks();
    }

    public void addNewBook() throws IOException, DAOException {
        bookDAO.addNewBookToLibrary();
    }
}
