package version_3;

import java.util.Objects;

public class Book {

    private static int GENERATEID = 0;

    private int id = GENERATEID++;
    private String name;
    private String authors;
    private String content;
    private String description;
    private int countPage;
    private int yearPublishing;
    private Type type;


    public Book(String name, String authors, String content, String description, int countPage, int yearPublishing, Type type) {
        this.name = name;
        this.authors = authors;
        this.content = content;
        this.description = description;
        this.countPage = countPage;
        this.yearPublishing = yearPublishing;
        this.type = type;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return countPage == book.countPage &&
                yearPublishing == book.yearPublishing &&
                Objects.equals(name, book.name) &&
                Objects.equals(authors, book.authors) &&
                Objects.equals(content, book.content) &&
                Objects.equals(description, book.description) &&
                type == book.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, authors, content, description, countPage, yearPublishing, type);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", authors='" + authors + '\'' +
                ", content='" + content + '\'' +
                ", description='" + description + '\'' +
                ", countPage=" + countPage +
                ", yearPublishing=" + yearPublishing +
                ", kind=" + type +
                '}';
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDescription(String description) {

        this.description = description;


    }

    public void setCountPage(int countPage) {
        if (countPage > 0) {

            this.countPage = countPage;
        } else {
            throw new IllegalArgumentException("Count page of book more 1");
        }
    }

    public void setYearPublishing(int yearPublishing) {
        this.yearPublishing = yearPublishing;
    }

    public void setType(Type type) {
        this.type = type;
    }



    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthors() {
        return authors;
    }

    public String getContent() {
        return content;
    }

    public int getCountPage() {
        return countPage;
    }

    public int getYearPublishing() {
        return yearPublishing;
    }

    public Type getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
