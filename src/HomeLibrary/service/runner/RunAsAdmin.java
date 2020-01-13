package HomeLibrary.service.runner;

import HomeLibrary.entity.book.Book;
import HomeLibrary.entity.library.Library;
import HomeLibrary.service.LibraryLogic;
import HomeLibrary.entity.user.User;
import HomeLibrary.entity.user.Users;
import HomeLibrary.view.Print;
import HomeLibrary.view.View;

import java.io.IOException;
import java.util.Scanner;

public class RunAsAdmin {
    public void runAsAdmin(Users users, Library library, User user) throws IOException {

        Scanner scanner = new Scanner(System.in);

        int choiceInt = 0;
        int check = 0;

        boolean bool = false;
        do {
            Print.printTheMessage("" +
                    "1 - Watch library, " +
                    "2 - Add book to the library, " +
                    "3 - Delete book, " +
                    "4 - Quit");

            if (scanner.hasNextInt()) {

                choiceInt = scanner.nextInt();

                if (choiceInt > 0 & choiceInt < 5) {
                    check = choiceInt;
                    bool = true;
                }
            } else scanner.next();
        } while (!bool);


        switch (check) {
            case (1):
                View.printBooks(library);
                runAsAdmin(users, library, user);
                break;

//--------------------------------------------------------------------------------------------------------------
            case (2):
                Book newBook = LibraryLogic.addNewBookToLibrary(library);
                LibraryLogic.saveLibraryToTXT(newBook);
                runAsAdmin(users, library, user);
                break;
//--------------------------------------------------------------------------------------------------------------

            case (3):

                View.printBooks(library);
                Print.printTheMessage("Choose a number of book for deleting..");

                int numberOfBook = scanner.nextInt();

                library = LibraryLogic.deleteBookFromLibrary(numberOfBook, library);
                LibraryLogic.saveLibraryToTXTDelete(library);
                View.printBooks(library);
                runAsAdmin(users, library, user);
                break;

//--------------------------------------------------------------------------------------------------------------

            case (4):
                break;
//--------------------------------------------------------------------------------------------------------------
        }
    }
}
