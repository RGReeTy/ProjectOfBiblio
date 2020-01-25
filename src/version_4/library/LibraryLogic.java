package version_4.library;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LibraryLogic {

    public void addBooksFromTXT(String address, Library library) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File(address), "UTF-8");
        String BooksInString = scanner.useDelimiter("\\A").next();

        Pattern pattern = Pattern.compile("([а-яА-яA-Za-z0-9 .,?!\"]+)\\+([а-яА-яA-Za-z0-9 .,?!\"]+)\\+([а-яА-яA-Za-z0-9 .,?!\"]+)\\+(.+)@");
        Matcher matcher = pattern.matcher(BooksInString);

        while (matcher.find()) {

            library.addBook(new Book(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4)));

        }
    }

    public Book findTheBook(String string, Library library) {
        Pattern pattern = Pattern.compile(string.toLowerCase());

        for (Book book : library.getBooks()) {
            Matcher matcher = pattern.matcher(book.toString().toLowerCase());
            if (matcher.find()) {
                return book;
            }
        }
        return null;
    }

    public Book addNewBookToLibrary(Library library) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите Название книги");
        String bookName = reader.readLine();
        System.out.println("Введите Автора");
        String author = reader.readLine();
        System.out.println("Введите тип книги (электронная,бумажная)");
        String typeOfBook = reader.readLine();
        System.out.println("Введите описание");
        String info = reader.readLine();

        return new Book(bookName, author, typeOfBook, info);

    }

    public void saveLibraryToTXT(Book book) {
        try (FileWriter writer = new FileWriter("C:\\Users\\Valeronij\\IdeaProjects\\classes\\out\\production\\classes\\by\\epam\\tasks6\\task1\\library\\Library.txt", true)) {

            writer.append(System.lineSeparator());
            writer.write(book.getBookName() + " + " + book.getAuthor() + " + " + book.getTypeOfBook() + " + " + book.getAboutBook() + "@");

            writer.flush();
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }

    public void saveLibraryToTXTDelete(Library library) {
        try (FileWriter writer = new FileWriter("C:\\Users\\Valeronij\\IdeaProjects\\classes\\out\\production\\classes\\by\\epam\\tasks6\\task1\\library\\Library.txt", false)) {
            for (Book book : library.getBooks()) {
                writer.append(System.lineSeparator());
                writer.write(book.getBookName() + " + " + book.getAuthor() + " + " + book.getTypeOfBook() + " + " + book.getAboutBook() + "@");
            }


            writer.flush();
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }

    public Library deleteBookFromLibrary(int numberOfBook, Library library) {
        Library newLibrary = new Library();

        for (int i = 0; i < library.getBooks().length; i++) {
            if (i != numberOfBook) {
                newLibrary.addBook(library.getBooks(i));
            }

        }
        return newLibrary;
    }


}
