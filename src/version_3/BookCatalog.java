package version_3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookCatalog {
    private String nameFile="sourceBook.txt";
    private List<Book> books = new ArrayList<>();
    private ReadWriteFiles readWriteFiles;

    public BookCatalog() throws IOException {

        createBooks();

    }

    public List<Book> getBooks() {
        return books;
    }

    private void createBooks() throws IOException {

        readWriteFiles = new ReadWriteFiles(nameFile);
        Book book;
        List<String> lines;

        lines = readWriteFiles.read();

        Iterator<String> stringIterator = lines.iterator();

        while (stringIterator.hasNext()) {
            try {

                book = new Book(stringIterator.next(),
                        stringIterator.next(),
                        stringIterator.next(),
                        stringIterator.next(),
                        Integer.parseInt(stringIterator.next()),
                        Integer.parseInt(stringIterator.next()),
                        Type.Paper.toString().equals(stringIterator.next()) ? Type.Paper : Type.Electronic);

                books.add(book);

            } catch (NumberFormatException e) {
                System.err.println("Incorrect data the book");
            }


            save();

        }
    }

    public void save(){

        ReadWriteFiles readWriteFiles = new ReadWriteFiles("books");
        List<String> linesForSave=new ArrayList<>();

        for (Book book: books) {

            linesForSave.add(book.getName());
            linesForSave.add(book.getAuthors());
            linesForSave.add(book.getContent());
            linesForSave.add(book.getDescription());
            linesForSave.add(Integer.toString(book.getCountPage()));
            linesForSave.add(Integer.toString(book.getYearPublishing()));
            linesForSave.add(book.getType().toString());

        }

        try {
            readWriteFiles.write(linesForSave);
        } catch (IOException e) {
            System.err.println("Error while saving to file");
        }

    }

    public List<Book> searchByName(String partName) {

        List<Book> booksOut = new ArrayList<>();

        for (Book book : books) {

            if (book.getName().contains(partName)) {
                booksOut.add(book);
            }

        }
        return booksOut;

    }

    public List<Book> searchByAuthor(String author) {

        List<Book> booksOut = new ArrayList<>();

        for (Book book : books) {

            if (book.getAuthors().contains(author)) {
                booksOut.add(book);
            }

        }
        return booksOut;

    }

    public List<Book> searchByYearPublishing(int yearPublishing) {

        List<Book> booksOut = new ArrayList<>();

        for (Book book : books) {

            if (book.getYearPublishing() == yearPublishing) {
                booksOut.add(book);
            }

        }
        return booksOut;

    }

    public boolean add(Book book) {

        if (book != null) {

            books.add(book);
            save();
            return true;

        }

        return false;
    }

    public boolean remove(Book book) {

        if (book != null) {

            if (books.remove(book)) {

                return true;
            }
        }
        return false;
    }

    public int size(){
        return books.size();
    }


}
