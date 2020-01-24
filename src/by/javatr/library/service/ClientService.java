package by.javatr.library.service;

import by.javatr.library.bean.Book;
import by.javatr.library.dao.daoimpl.BookDAOImpl;
import by.javatr.library.dao.daoimpl.UserDAOImpl;
import by.javatr.library.dao.exception.DAOException;
import by.javatr.library.service.exception.ServiceException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientService {
    UserDAOImpl userDAO = new UserDAOImpl();
    BookDAOImpl bookDAO = new BookDAOImpl();
    private ArrayList<Book> books;

    public ClientService() throws FileNotFoundException, DAOException {
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
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter book's name..");
        String bookName = reader.readLine();
        System.out.println("Enter author's name..");
        String author = reader.readLine();
        System.out.println("Enter type of book..");
        String typeOfBook = reader.readLine();
        System.out.println("What about this book..");
        String info = reader.readLine();

        bookDAO.addBook(new Book(bookName, author, typeOfBook, info));
        bookDAO.saveLibraryToTXT();
    }

    public ArrayList<Book> findTheBook() {
        String textToFind = null;
        ArrayList<Book> findingBooks = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter what you want to find..");
        try {
            textToFind = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Pattern pattern = Pattern.compile(textToFind.toLowerCase());

        for (Book book : books) {
            Matcher matcher = pattern.matcher(book.toString().toLowerCase());
            if (matcher.find()) {
                findingBooks.add(book);
            }
        }
        if (findingBooks.size() == 0) {
            return null;
        } else {
            return findingBooks;
        }
    }

    public void deleteBook() {
        if (userDAO.getCurrentUser().isAdmin()) {
            try {
                bookDAO.deleteBook();
            } catch (DAOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("\nOnly administrator can delete books!");
        }
    }
}
