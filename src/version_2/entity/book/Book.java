package version_2.entity.book;


import java.util.Arrays;

public class Book {

    private static int genId = 1;
    private int id;
    private String title;
    private String[] authors;
    private String publisher;
    private int year;
    private int pageNum;
    private Type type;

    public Book() {
        this.id = genId++;
        this.title = "";
        this.authors = new String[]{""};
        this.publisher = "";
        this.year = 0;
        this.pageNum = 0;
        this.type = Type.PAPER;
    }

    public Book(String title, String[] authors, String publisher, int year, int pageNum, Type type) {
        this();
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.year = year;
        this.pageNum = pageNum;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (year != book.year) return false;
        if (pageNum != book.pageNum) return false;
        if (title != null ? !title.equals(book.title) : book.title != null) return false;
        if (!Arrays.equals(authors, book.authors)) return false;
        if (publisher != null ? !publisher.equals(book.publisher) : book.publisher != null) return false;
        return type == book.type;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + Arrays.hashCode(authors);
        result = 31 * result + (publisher != null ? publisher.hashCode() : 0);
        result = 31 * result + year;
        result = 31 * result + pageNum;
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format("id: %d\t title: %s\t authors: %s\t publisher: %s\t year: %d\t pageNum: %d\t format: %s"
                , id, title, Arrays.toString(authors), publisher, year, pageNum, type.name().toLowerCase());
    }
}
