package version_4.view;


import version_4.library.Book;
import version_4.library.Library;

public class View {
    public void printBooks(Library library){
        int i = 0;
        for (Book book:library.getBooks()) {
            System.out.println(i+" "+book.toString());
            i++;
        }
    }
}
