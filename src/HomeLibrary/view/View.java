package HomeLibrary.view;


import HomeLibrary.library.Book;
import HomeLibrary.library.Library;

public class View {
    public void printBooks(Library library){
        int i = 0;
        for (Book book:library.getBooks()) {
            System.out.println(i+" "+book.toString());
            i++;
        }
    }
}
