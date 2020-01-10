package HomeLibrary.view;


import HomeLibrary.entity.book.Book;
import HomeLibrary.entity.library.Library;

public class View {

    public static void printBooks(Library library) {
        int i = 0;
        for (Book book : library.getBooks()) {
            Print.printTheMessage(i + " " + book.toString());
            i++;
        }
    }
}