package HomeLibrary.service;

import HomeLibrary.entity.book.Book;
import HomeLibrary.entity.library.Library;
import HomeLibrary.view.Print;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LibraryLogic {

    public static void addBooksFromTXT(String address, Library library) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File(address), "UTF-8");
        String BooksInString = scanner.useDelimiter("\\A").next(); // \\A - The beginning of the input(docs.oracle.com)

        Pattern pattern = Pattern.compile("([а-яА-яA-Za-z0-9 .,?!\"]+)\\+([а-яА-яA-Za-z0-9 .,?!\"]+)\\+([а-яА-яA-Za-z0-9 .,?!\"]+)\\+(.+)@");
        Matcher matcher = pattern.matcher(BooksInString);

        while (matcher.find()) {
            library.addBook(new Book(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4)));
        }
    }

    public static ArrayList<Book> findTheBook(String textToFind, Library library) {
        Pattern pattern = Pattern.compile(textToFind.toLowerCase());
        ArrayList<Book> findingBooks = new ArrayList<>();

        for (Book book : library.getBooks()) {
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

    public static Book addNewBookToLibrary(Library library) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Print.printTheMessage("Enter book's name..");
        String bookName = reader.readLine();
        Print.printTheMessage("Enter author's name..");
        String author = reader.readLine();
        Print.printTheMessage("Enter type of book..");
        String typeOfBook = reader.readLine();
        Print.printTheMessage("What about this book..");
        String info = reader.readLine();

        return new Book(bookName, author, typeOfBook, info);

    }

    public static void saveLibraryToTXT(Book book) {
        try (FileWriter writer = new FileWriter("src\\HomeLibrary\\file\\Library.txt", true)) {

            writer.append(System.lineSeparator());
            writer.write(book.getBookName() + " + " + book.getAuthor() + " + " + book.getTypeOfBook() + " + " + book.getAboutBook() + "@");
            writer.flush();
        } catch (IOException ex) {
            Print.printTheMessage(ex.getMessage());
        }
    }

    public static void saveOfferingBookToTXT(Book book) {
        try (FileWriter writer = new FileWriter("src\\HomeLibrary\\file\\OfferingBook.txt", true)) {

            writer.append(System.lineSeparator());
            writer.write(book.getBookName() + " + " + book.getAuthor() + " + " + book.getTypeOfBook() + " + " + book.getAboutBook() + "@");
            writer.flush();
        } catch (IOException ex) {
            Print.printTheMessage(ex.getMessage());
        }
    }

    public static void saveLibraryToTXTDelete(Library library) {
        try (FileWriter writer = new FileWriter("src\\HomeLibrary\\file\\Library.txt", false)) {
            for (Book book : library.getBooks()) {
                writer.append(System.lineSeparator());
                writer.write(book.getBookName() + " + " + book.getAuthor() + " + " + book.getTypeOfBook() + " + " + book.getAboutBook() + "@");
            }
            writer.flush();
        } catch (IOException ex) {
            Print.printTheMessage(ex.getMessage());
        }
    }

    public static Library deleteBookFromLibrary(int numberOfBook, Library library) {
        Library newLibrary = new Library();

        for (int i = 0; i < library.getBooks().length; i++) {
            if (i + 1 != numberOfBook) {
                newLibrary.addBook(library.getBooks(i));
            }
        }
        return newLibrary;
    }

    public static boolean isExist(Library library, int index) {
        return index <= library.getBooks().length;
    }

    public static boolean isOfferingBookContainsAnyOffer() throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("src\\HomeLibrary\\file\\OfferingBook.txt"), "UTF-8");
        return scanner.hasNextLine();
    }

    public static Book showBookFromUsersOffer() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src\\HomeLibrary\\file\\OfferingBook.txt"), "UTF-8");
        String BooksInString = scanner.useDelimiter("\\A").next(); // \\A - The beginning of the input(docs.oracle.com)
        Pattern pattern = Pattern.compile("([а-яА-яA-Za-z0-9 .,?!\"]+)\\+([а-яА-яA-Za-z0-9 .,?!\"]+)\\+([а-яА-яA-Za-z0-9 .,?!\"]+)\\+(.+)@");
        Matcher matcher = pattern.matcher(BooksInString);
        Book newBook = null;
        while (matcher.find()) {
            newBook = new Book(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4));
        }
        return newBook;
    }
}