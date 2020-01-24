package by.javatr.library.dao.daoimpl;

import by.javatr.library.bean.Book;
import by.javatr.library.dao.BookDAO;
import by.javatr.library.dao.FileDAO;
import by.javatr.library.dao.exception.DAOException;

import java.io.*;
import java.util.ArrayList;
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
    public void deleteBook() throws DAOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose number of book for deleting:");
        int bookIDForDeleting = scanner.nextInt();
        if (bookIDForDeleting <= books.size() & bookIDForDeleting > 0)
            books.remove(bookIDForDeleting - 1);
        saveLibraryToTXT();
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

    public void saveLibraryToTXT() {
        try (FileWriter writer = new FileWriter("src\\by\\javatr\\library\\resource\\library\\Library.txt", false)) {
            for (Book book : books) {
                writer.append(System.lineSeparator());
                writer.write(book.getBookName() + " + " + book.getAuthor() + " + " + book.getTypeOfBook() + " + " + book.getAboutBook() + "@");
            }
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
