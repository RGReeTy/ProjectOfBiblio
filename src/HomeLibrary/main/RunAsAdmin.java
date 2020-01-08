package HomeLibrary.main;

import HomeLibrary.library.Book;
import HomeLibrary.library.Library;
import HomeLibrary.library.LibraryLogic;
import HomeLibrary.user.User;
import HomeLibrary.user.Users;
import HomeLibrary.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class RunAsAdmin {
    public void runAsAdmin(Users users, Library library, User user) throws IOException {

        LibraryLogic libraryLogic = new LibraryLogic();
        View view = new View();
        Scanner scanner = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String choice = null;

        boolean bool = false;
        do {
            System.out.println("1 - Посмотреть каталог, 2 - Добавить книгу , 3 - Удалить книгу, 4 - Выйти");


            if (scanner.hasNextInt()) {

                int choiceInt = scanner.nextInt();

                if (choiceInt > 0 && choiceInt < 5) {
                    choice = Integer.toString(choiceInt);
                    bool = true;
                }
            } else scanner.next();

        } while (!bool);


        switch (choice) {

            case ("1"):

                view.printBooks(library);
                runAsAdmin(users, library, user);

                break;


            //////////////////////////////////////////////////////////////////////////

            case ("2"):

                Book newBook = libraryLogic.addNewBookToLibrary(library);

//                //EmailAuthenticator emailAuthenticator = new EmailAuthenticator(user.geteMail(), "AdminPassword");
//                //SendEmail sendEmail = new SendEmail();
//               // for (User userToSend : users.getUsers()) {
//                    sendEmail.sendEmailAsAdministrator(emailAuthenticator, userToSend, newBook);
//                }
                libraryLogic.saveLibraryToTXT(newBook);
                runAsAdmin(users, library, user);


                break;
///////////////////////////////

            case ("3"):

                view.printBooks(library);
                System.out.println("Выберите номер книги для удаления");

                int numberOfBook = scanner.nextInt();

                library = libraryLogic.deleteBookFromLibrary(numberOfBook, library);
                libraryLogic.saveLibraryToTXTDelete(library);


                view.printBooks(library);
                runAsAdmin(users, library, user);

                break;

            ///////////////////////////////


            case ("4"):

                break;

            ///////////////////////////////

        }
    }
}
