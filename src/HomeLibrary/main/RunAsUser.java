package HomeLibrary.main;


import HomeLibrary.library.Library;
import HomeLibrary.library.LibraryLogic;
import HomeLibrary.user.User;
import HomeLibrary.user.Users;
import HomeLibrary.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class RunAsUser {
    public void runAsUser(User user, Users users, Library library) throws IOException {

        LibraryLogic libraryLogic = new LibraryLogic();
        View view = new View();
        Scanner scanner = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String choice = null;

        boolean bool = false;
        do {
            System.out.println("1 - Watch library, 2 - Find book in the library, 3 - Ask to add book to administartor, 4 - Quit");

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
                runAsUser(user, users, library);
                break;

            //////////////////////////////////////////////////////////////////////////

            case ("2"):

                System.out.println("Что вы ищите?");
                String stringFind = scanner.next();
                if (libraryLogic.findTheBook(stringFind, library) != null) {
                    System.out.println(libraryLogic.findTheBook(stringFind, library).toString());
                }
                runAsUser(user, users, library);


                break;
///////////////////////////////

            case ("3"):

                System.out.println("Напишите о книге которую хотите предложить");
                String text = reader.readLine();

                String password = "Password";
                System.out.println("Спасибо");

                runAsUser(user, users, library);

                break;

            ///////////////////////////////
            ///////////////////////////////

            case ("4"):
                System.out.println("Выход из программы");
                break;

            ///////////////////////////////

        }
    }
}
