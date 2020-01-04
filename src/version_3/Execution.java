package version_3;


import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static version_3.UserService.*;

/**
 * Создать консольное приложение "Учет книг в домешней билиотеке".
 * Общие требования к заданию
 * Система учитывает книги как в электронном, так и в бумажном варианте
 * Существующие роли: пользователь и администратор
 * Пользователь может просматривать книги в каталоге книг, осуществлять поиск книг в каталоге.
 * Администратор может модифицировать каталог.
 * При просмотре каталога желательно реализовать постраничный просмотр
 * Пользователь может предложить добавить книгу в библиотеку, переслав ее администратору на e-mail.
 * Каталог книг хранится в текстовом файле
 * Данные аутентификации пользователей хранятся в текстовом файле. Пароль не хранится в открытом виде
 */

public class Execution {
    private BookCatalog bookCatalog;
    private UserCatalog userCatalog;
    private User userSignIn = null;
    private List<BookSuggest> booksSuggestForAdmin = new ArrayList<>();
    private Book selectedBook;
    private List<Book> findBooks;


    private int choiceSignIn, choice;
    private boolean isAdmin = false, isUser = false;


    public Execution() {


        try {

            bookCatalog = new BookCatalog();

        } catch (IOException e) {
            System.err.println("File is not found for book");
        }


        try {

            userCatalog = new UserCatalog();

        } catch (IOException e) {
            System.err.println("File is not found for user");
        } catch (NoSuchElementException e) {
            System.err.println("Error");
        }

    }

    /**
     * Аутентификация или регистрация пользователя
     */
    public void authentication() {

        String infoSignInMenu = "\nInput the number:\n" +
                "   1- Sign in\n" +
                "   2- Registration\n" +
                "   other- exit";

        while (!isUser) {

            choiceSignIn = GetValues.getIntValue(infoSignInMenu, 1, 3);

            switch (choiceSignIn) {
                case 1:

                    while (!isUser) {
                        String login = GetValues.getStringValue("Input you login");
                        String password = GetValues.getStringLine("Input you password");

                        userSignIn = userCatalog.searchByLoginAndPassword(login, password);

                        if (userSignIn != null) {

                            isUser = true;
                            isAdmin = userSignIn.getRole().equals(Role.Admin);

                        } else {
                            System.err.println("Incorrect  login or password");
                        }
                    }

                    System.err.println("Congratulations " + userSignIn.getFirstName());
                    break;
                case 2:

                    registration();
                    System.out.println("You are registered");
                    break;

                default:
                    System.exit(0);

            }


        }


    }


    public void action() {

        String infoStartMenu = "\nInput the number:\n" +
                "   1- Choose a book among all bookCatalog\n" +
                "   2- Search book\n" +
                (isAdmin ? "   3- View suggested book by users\n" : "   3- Suggest to add new book\n") +
                (isAdmin ? "   4- Update a book\n" : "   4- Sign out\n") +
                (isAdmin ? "   5- Add a book\n" : "   other- Exit   \n") +
                (isAdmin ? "   6- Sign out\n" : "") +
                (isAdmin ? "   other- Exit\n" : "");


        while (isUser) {
            System.err.printf("\n%30s", userSignIn.getFirstName());
            choice = GetValues.getIntValue(infoStartMenu);


            switch (choice) {
                case 1:

                    selectedBook = selectionBook(bookCatalog.getBooks());
                    printBook();
                    break;

                case 2:

                    search();

                    break;
                case 3:

                    if (isAdmin) {
                        suggestedBooks();
                    } else {
                        suggestAddBook();
                    }

                    break;
                case 4:

                    if (isAdmin) {
                        modification();
                    } else {
                        signOut();
                    }

                    break;
                case 5:

                    if (isAdmin) {

                        addBook();
                        break;

                    } else {

                        System.exit(0);
                        break;

                    }

                case 6:

                    if (isAdmin) {

                        signOut();
                        break;

                    }

                default:
                    System.exit(0);

            }
        }

    }

    public void modification() {
        int choice;

        String infoMenu = "\nInput the number:\n" +
                "   1- Update the name of the book\n" +
                "   2- Update the authors of the book\n" +
                "   3- Update the content of the book\n" +
                "   4- Update the description of the book\n" +
                "   5- Update the count page of the book\n" +
                "   6- Update the year publication of the book\n" +
                "   7- Update the type of the book\n" +
                "   8- Sign out\n" +
                "   other- back";

        while (isUser) {

            selectedBook = selectionBook(bookCatalog.getBooks());

            choice = GetValues.getIntValue(infoMenu);

            switch (choice) {
                case 1:

                    String newName = GetValues.getStringLine("\nInput new name for the book \"" + selectedBook.getName() + "\"");
                    selectedBook.setName(newName);

                    bookCatalog.save();

                    System.out.println("Name was updated");
                    break;

                case 2:

                    String newAuthors = GetValues.getStringLine("Old authors: " + selectedBook.getAuthors() +
                            "\nInput all new authors for the book \"" + selectedBook.getName() + "\"");
                    selectedBook.setAuthors(newAuthors);

                    bookCatalog.save();

                    System.out.println("Authors were updated");
                    break;

                case 3:

                    String newContent = GetValues.getStringLine("\nInput new content for the book \"" + selectedBook.getName() + "\"");
                    selectedBook.setContent(newContent);

                    bookCatalog.save();

                    break;
                case 4:

                    String newDescription = GetValues.getStringUntilExit("Input new description for the book \"" + selectedBook.getName() + "\"");
                    selectedBook.setDescription(newDescription);

                    List<User> admins = new ArrayList<>();

                    for (User user : userCatalog.getUsers()) {

                        if (user.getRole().equals(Role.Admin)) {
                            admins.add(user);
                        }

                    }

                    bookCatalog.save();

                    //MailService.send(admins, selectedBook, "Book was added by administrator");
                    System.out.println("Description was updated");
                    break;

                case 5:

                    int newCountPage = GetValues.getIntValue("Input new count page for the book \"" + selectedBook.getName() + "\"");
                    selectedBook.setCountPage(newCountPage);

                    bookCatalog.save();

                    System.out.println("Count page was updated");
                    break;
                case 6:

                    int newYearPublication = GetValues.getIntValue("Input new year publication for the book \"" + selectedBook.getName() + "\"");
                    selectedBook.setYearPublishing(newYearPublication);

                    bookCatalog.save();

                    System.out.println("Year publication was updated");
                    break;
                case 7:

                    String newKindString = GetValues.getStringLine("Input new kind of the book \"" + selectedBook.getName() + "\"");
                    Type newType = newKindString.equals(Type.Paper.toString()) ? Type.Paper : Type.Electronic;
                    selectedBook.setType(newType);

                    bookCatalog.save();

                    System.out.println("Kind book was updated");
                    break;

                case 8:

                    userSignIn = null;
                    isUser = false;
                    isAdmin = false;
                    break;


                default:
                    return;
            }

        }

    }

    public void registration() {
        String firstName;
        String lastName;
        String login;
        String email;
        String password;
        String numberPhone;
        String role = "user";

        do {
            firstName = GetValues.getStringValue("Input your first name");

        } while (!checkFirstName(firstName));


        do {
            lastName = GetValues.getStringValue("Input your last name");

        } while (!checkLastName(lastName));


        do {
            login = GetValues.getStringLine("Input your login");

        } while (!checkLogin(login) || hasLoginInList(login, userCatalog.getUsers()));


        do {
            email = GetValues.getStringLine("Input your email");

        } while (!checkEmail(email));


        do {
            password = GetValues.getStringLine("Input your password");

        } while (!checkPassword(password));


        do {
            numberPhone = String.valueOf(GetValues.getLongValue("Input your number phone"));

        } while (!checkNumberPhone(numberPhone));


        userCatalog.add(firstName, lastName, login, email, password, numberPhone, role);
    }

    public void suggestAddBook() {

        Book book = createBook();

        List<User> collect = userCatalog.getUsers().stream().filter(s -> s.getRole().equals(Role.Admin)).collect(Collectors.toList());

        //MailService.send(collect, book, "You were sent a book from a user to add to the library");

        booksSuggestForAdmin.add(new BookSuggest(book, userSignIn));

    }


    public void printBook() {

        System.out.print("Name            : ");
        System.out.println(selectedBook.getName());
        System.out.print("Authors         : ");
        System.out.println(selectedBook.getAuthors());
        System.out.print("Description     : ");
        System.out.println(selectedBook.getDescription());
        System.out.print("Count page      : ");
        System.out.println(selectedBook.getCountPage());
        System.out.print("Year publication: ");
        System.out.println(selectedBook.getYearPublishing());
        System.out.print("Kind            : ");
        System.out.println(selectedBook.getType());


        String infoSearch = "\nInput the number:\n" +
                "   1- View book's content\n" +
                "   other- Back \n";


        System.err.printf("\n%30s", userSignIn.getFirstName());

        int choicePrint = GetValues.getIntValue(infoSearch);

        if (choicePrint == 1) {
            printContent();
        } else {
            selectedBook = null;
        }


    }

    public void printContent() {

        int countCharInLine = 60;
        int countLineOnPage = 30;


        List<List<String>> pages = new ArrayList<>();
        List<String> lines = new ArrayList<>();

        String[] words = selectedBook.getContent().replaceAll("\n", "\n ").split(" ");
        StringBuilder line = new StringBuilder();

        line.append("\t");

        System.out.print("Content the book: ");

        for (int i = 0; i < words.length; i++) {

            if (lines.size() == countLineOnPage) {

                pages.add(lines);
                lines = new ArrayList<>();


            }

            if ((line.length() + words[i].length()) > countCharInLine) {

                lines.add(line.toString());
                line.setLength(0);
                i--;


            } else {

                if (words[i].contains("\n")) {
                    line.append(words[i].replace("\n", ""));
                    lines.add(line.toString());
                    line.setLength(0);
                    line.append("\t");

                } else {

                    line.append(words[i]).append(" ");
                }
            }


        }
        lines.add(line.toString());

        pages.add(lines);

        int numberPage = 0;
        int choiceAction;

        String previousPage, nextPage;

        while (true) {

            previousPage = numberPage == 0 ? "-" : "1- previous page";
            nextPage = numberPage >= pages.size() - 1 ? "-" : "2- next page";

            System.out.println();

            for (String s : pages.get(numberPage)) {

                System.out.println(s);

            }

            System.out.printf("\n%-20s %10s \n%10s", previousPage, nextPage, "other- exit\n");

            choiceAction = GetValues.getIntValue("\nInput the number");

            if (choiceAction == 1) {
                if (numberPage > 0) {
                    numberPage--;
                } else {
                    return;
                }
            } else if (choiceAction == 2) {
                if (numberPage < pages.size() - 1) {
                    numberPage++;
                } else {
                    return;
                }
            } else {

                return;

            }
        }

    }

    private void suggestedBooks() {

        if (isAdmin) {

            List<Book> books = new ArrayList<>();

            for (BookSuggest bookSuggest : booksSuggestForAdmin) {

                books.add(bookSuggest.getBook());

            }

            selectedBook = selectionBook(books);

            int move = GetValues.getIntValue("       Name- " + selectedBook.getName() + "\n" +
                    "       authors- " + selectedBook.getAuthors() + "\n" +
                    "       description- " + selectedBook.getDescription() + "\n" +
                    "Input the number\n" +
                    "   1- view content\n" +
                    "   other- next step\n");


            if (move == 1) {
                printContent();
            }

            move = GetValues.getIntValue("\nInput the number\n" +
                    "   1- add book to catalog\n" +
                    "   other- don't add book to catalog");

            if (move == 1) {
                bookCatalog.add(selectedBook);
                //MailService.send(userCatalog.getUsers(), selectedBook, "Book was added by administrator");
            }


        }

    }

    private Book createBook() {
        Book book;

        String name = GetValues.getStringLine("Input the name of the book");
        String authors = GetValues.getStringLine("Input the authors of the book");
        String content = GetValues.getStringUntilExit("Input the content of the book");
        String description = GetValues.getStringUntilExit("Input the description of the book");
        int countPage = GetValues.getIntValue("Input count page of the book", 1, Integer.MAX_VALUE);
        int yearPublication = GetValues.getIntValue("Input year publication of the book", 1, LocalDate.now().getYear());
        Type type = GetValues.getIntValue("Input kind of the book\n" +
                "   1- Electronic\n" +
                "   2- Paper\n", 1, 2) == 1 ? Type.Electronic : Type.Paper;

        book = new Book(name, authors, content, description, countPage, yearPublication, type);
        return book;
    }

    private void addBook() {

        Book book = createBook();

        int localChoice = GetValues.getIntValue("Input the number\n" +
                "   1- add\n" +
                "   2-back");

        if (localChoice == 1) {
            bookCatalog.add(book);
        }


    }

    private boolean search() {

        String infoSearch = "\nInput the number:\n" +
                "   1- Search books by name\n" +
                "   2- Search books by authors\n" +
                "   3- Search books by year publication\n" +
                "   other- Back \n";


        System.err.printf("\n%30s", userSignIn.getFirstName());
        choice = GetValues.getIntValue(infoSearch);


        switch (choice) {
            case 1:

                String partName = GetValues.getStringLine("Input the part of name");

                findBooks = bookCatalog.searchByName(partName);
                selectedBook = selectionBook(findBooks);

                break;

            case 2:

                String partAuthors = GetValues.getStringLine("Input an author");

                findBooks = bookCatalog.searchByAuthor(partAuthors);
                selectedBook = selectionBook(findBooks);

                break;
            case 3:

                int yearPublication = GetValues.getIntValue("Input the year");

                findBooks = bookCatalog.searchByYearPublishing(yearPublication);
                selectedBook = selectionBook(findBooks);

                break;

            default:
                selectedBook = null;
                return false;


        }

        printBook();

        return true;
    }

    private boolean signOut() {

        isUser = false;
        isAdmin = false;
        userSignIn = null;
        return true;

    }


    public static Book selectionBook(List<Book> books) {

        int countBookOnPage = 15;
        int page = 0;
        int choiceBook;
        int number = 1;
        int first, last;

        Book selectedBook;
        String previousPage, nextPage;

        while (true) {
            first = countBookOnPage * page;
            last = countBookOnPage * (page + 1) <= books.size() ? countBookOnPage * (page + 1) : books.size();

            for (int i = first; i < last; i++) {

                System.out.println("    " + number++ + "- " + books.get(i).getName());

            }


            if (page == 0) {
                previousPage = "_                        ";
            } else {
                previousPage = (number) + "-previous page         ";
            }

            if ((page + 1) * countBookOnPage >= books.size()) {
                nextPage = "_";
            } else {
                nextPage = (number + 1) + "- next page";
            }

            System.out.println(previousPage + nextPage);

            while (true) {

                choiceBook = GetValues.getIntValue("Input the number");

                if (!(choiceBook > 0 && choiceBook <= number + 1)) {
                    System.err.println("Incorrect value");
                } else {
                    break;
                }
            }

            if (choiceBook == number) {
                page--;
            } else if (choiceBook == number + 1) {
                page++;
            } else {
                selectedBook = books.get(page * countBookOnPage + choiceBook - 1);
                return selectedBook;
            }


            number = 1;


        }

    }
}
