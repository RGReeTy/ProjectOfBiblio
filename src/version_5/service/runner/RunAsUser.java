package version_5.service.runner;

import HomeLibrary.entity.book.Book;
import HomeLibrary.entity.library.Library;
import HomeLibrary.entity.user.User;
import HomeLibrary.entity.user.Users;
import HomeLibrary.view.Print;
import version_5.service.LibraryLogic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class RunAsUser {
    public void runAsUser(User user, Users users, Library library) throws IOException {

        Scanner scanner = new Scanner(System.in);

        int choiceInt;
        int check = 0;

        boolean bool = false;
        do {
            Print.printTheMessage("1 - Watch library, " +
                    "2 - Find book in the library, " +
                    "3 - Ask to add book to administartor, " +
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
                Print.printBooks(library);
                runAsUser(user, users, library);
                break;
//--------------------------------------------------------------------------------------------------------------

            case (2):
                Print.printTheMessage("What do you want to find?");
                String textToFind = scanner.next();
                ArrayList<Book> findingBooks = LibraryLogic.findTheBook(textToFind, library);
                if (findingBooks == null) {
                    Print.printTheMessage("Sorry, can't find any match like " + textToFind);
                } else {
                    Print.printTheMessage("Find next book(s):");
                    Print.printTheElementsOfArrayListOfBooks(findingBooks);
                }
                runAsUser(user, users, library);
                break;
//--------------------------------------------------------------------------------------------------------------
            case (3):
                Print.printTheMessage("Write something about book, which you want to offer..");
                Book offeringBook = LibraryLogic.addNewBookToLibrary(library);
                LibraryLogic.saveOfferingBookToTXT(offeringBook);
                Print.printTheMessage("Thanks");
                runAsUser(user, users, library);
                break;

//--------------------------------------------------------------------------------------------------------------
            case (4):
                Print.printTheMessage("Quiting..");
                break;
        }
    }
}
