package version_5.view;

import HomeLibrary.entity.book.Book;
import HomeLibrary.entity.library.Library;

import java.util.ArrayList;

public class Print {

    public static void printTheMessage(String text) {
        System.out.println(text);
    }


    public static void printTheElementsOfArrayListOfBooks(ArrayList<Book> text) {
        for (Object elem : text) {
            System.out.println(elem.toString());
        }
        System.out.println("-------------------------------------------------------------------------------------");
    }

    public static void printBooks(Library library) {
        int i = 1;
        for (Book book : library.getBooks()) {
            Print.printTheMessage(i + " " + book.toString());
            i++;
        }
    }

}
