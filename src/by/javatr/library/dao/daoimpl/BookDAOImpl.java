package by.javatr.library.dao.daoimpl;

import by.javatr.library.bean.Book;
import by.javatr.library.dao.BookDAO;
import by.javatr.library.dao.FileDAO;
import by.javatr.library.dao.exception.DAOException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookDAOImpl implements BookDAO, FileDAO {

    private ArrayList<Book> books;

    public BookDAOImpl() {
        books = new ArrayList<>();
        try {
            loadDataFromFile("src\\by\\javatr\\library\\resource\\library\\Library.txt");
        } catch (DAOException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Book> getAllBooks() {
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

    @Override
    public void loadDataFromFile(String address) throws DAOException, FileNotFoundException {
        Scanner scanner = new Scanner(new File(address), "UTF-8");
        String BooksInString = scanner.useDelimiter("\\A").next(); // \\A - The beginning of the input(docs.oracle.com)

        Pattern pattern = Pattern.compile("([а-яА-яA-Za-z0-9 .,?!\"]+)\\+([а-яА-яA-Za-z0-9 .,?!\"]+)\\+([а-яА-яA-Za-z0-9 .,?!\"]+)\\+(.+)@");
        Matcher matcher = pattern.matcher(BooksInString);

        while (matcher.find()) {
            addBook(new Book(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4)));
        }
    }
}
