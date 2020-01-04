package version_2.utils;


import version_2.entity.book.Book;
import version_2.entity.book.BookBuilder;
import version_2.entity.book.Type;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CatalogFileHandler {

    private static String csvFile = "src/Task_1_v2/data/catalog.csv";

    /**
     * Fills the collection by elements from a given csv file.
     *
     * @return the list of the books.
     */
    public static List<Book> loadBooks() {

        String line;
        String splitBy = ",";

        List<Book> books = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                String[] data = line.split(splitBy);

                Book book = new BookBuilder().withTitle(data[0])
                        .setPublisher(data[2])
                        .setYear(Integer.valueOf(data[3]))
                        .setPageNum(Integer.valueOf(data[4]))
                        .setType(Type.valueOf(data[5].toUpperCase()))
                        .build();

                if (data[1].contains("|")) {
                    String[] authors = data[1].split("\\|");
                    book.setAuthors(authors);
                } else {
                    book.setAuthors(new String[]{data[1]});
                }

                books.add(book);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }

    /**
     * Backup all catalog in the csv file after each catalog change.
     */
    public static void saveCatalog(List<Book> books) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile))) {
            for (Book book : books) {
                StringBuilder sb = new StringBuilder();
                sb.append(book.getTitle());
                sb.append(",");

                if (book.getAuthors().length > 1) {
                    Arrays.stream(book.getAuthors()).forEach(s -> sb.append(s).append("|"));
                    sb.deleteCharAt(sb.length() - 1);
                } else {
                    sb.append(book.getAuthors()[0]);
                }

                sb.append(",");
                sb.append(book.getPublisher());
                sb.append(",");
                sb.append(book.getYear());
                sb.append(",");
                sb.append(book.getPageNum());
                sb.append(",");
                sb.append(book.getType().name().toLowerCase());

                bw.write(sb.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
