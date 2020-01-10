package HomeLibrary.main;

import HomeLibrary.library.Book;
import HomeLibrary.library.Library;
import HomeLibrary.library.LibraryLogic;
import HomeLibrary.user.User;
import HomeLibrary.user.Users;
import HomeLibrary.view.Print;
import HomeLibrary.view.View;

import java.io.IOException;
import java.util.Scanner;

public class RunAsAdmin {
    public void runAsAdmin(Users users, Library library, User user) throws IOException {

        View view = new View();
        Scanner scanner = new Scanner(System.in);

        String choice = null;
        int choiceInt = 0;

        boolean bool = false;
        do {
            Print.printTheMessage("" +
                    "1 - Watch library, " +
                    "2 - Add book to the library, " +
                    "3 - Delete book, " +
                    "4 - Quit");

            if (scanner.hasNextInt()) {

                choiceInt = scanner.nextInt();

                if (choiceInt > 0 && choiceInt < 5) {
                    choice = Integer.toString(choiceInt);
                    bool = true;
                }
            } else scanner.next();

        } while (!bool);


        switch (choiceInt) {

            case (1):

                view.printBooks(library);
                runAsAdmin(users, library, user);

                break;


            //////////////////////////////////////////////////////////////////////////

            case (2):

                Book newBook = LibraryLogic.addNewBookToLibrary(library);

                LibraryLogic.saveLibraryToTXT(newBook);
                runAsAdmin(users, library, user);


                break;
///////////////////////////////

            case (3):

                view.printBooks(library);
                Print.printTheMessage("Choose a number of book for deleting..");

                int numberOfBook = scanner.nextInt();

                library = LibraryLogic.deleteBookFromLibrary(numberOfBook, library);
                LibraryLogic.saveLibraryToTXTDelete(library);


                view.printBooks(library);
                runAsAdmin(users, library, user);

                break;

            ///////////////////////////////


            case (4):

                break;

            ///////////////////////////////

        }
    }
}
