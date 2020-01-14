package HomeLibrary.service.runner;

import HomeLibrary.entity.book.Book;
import HomeLibrary.entity.library.Library;
import HomeLibrary.service.LibraryLogic;
import HomeLibrary.entity.user.User;
import HomeLibrary.entity.user.Users;
import HomeLibrary.view.Print;

import java.io.IOException;
import java.util.ArrayList;
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
                    "2 - Find book in the library, " +
                    "3 - Add book to the library, " +
                    "4 - Delete book, " +
                    "5 - Quit");

            if (scanner.hasNextInt()) {

                choiceInt = scanner.nextInt();

                if (choiceInt > 0 & choiceInt < 6) {
                    check = choiceInt;
                    bool = true;
                }
            } else scanner.next();
        } while (!bool);


        switch (check) {
            case (1):
                Print.printBooks(library);
                runAsAdmin(users, library, user);
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
                runAsAdmin(users, library, user);
                break;
//--------------------------------------------------------------------------------------------------------------
            case (3):
                Book newBook;
                if (LibraryLogic.isOfferingBookContainsAnyOffer()) {
                    Print.printTheMessage("Do you want to watch and to add book from user's offer? (Y/N)");
                    newBook = LibraryLogic.showBookFromUsersOffer();
                    Print.printTheMessage(newBook.toString());
                    if (scanner.next().equalsIgnoreCase("Y")) {
                        library.addBook(newBook);
                    }
                } else {
                    newBook = LibraryLogic.addNewBookToLibrary(library);
                }
                LibraryLogic.saveLibraryToTXT(newBook);
                runAsAdmin(users, library, user);
                break;
//--------------------------------------------------------------------------------------------------------------

            case (4):
                Print.printBooks(library);
                Print.printTheMessage("Choose a number of book for deleting..");

                int numberOfBook = scanner.nextInt();
                if (LibraryLogic.isExist(library, numberOfBook)) {
                    library = LibraryLogic.deleteBookFromLibrary(numberOfBook, library);
                    LibraryLogic.saveLibraryToTXTDelete(library);
                    Print.printBooks(library);
                } else {
                    Print.printTheMessage("Wrong index!");
                }
                runAsAdmin(users, library, user);
                break;

//--------------------------------------------------------------------------------------------------------------

            case (5):
                break;
//--------------------------------------------------------------------------------------------------------------
        }
    }
}
