package HomeLibrary.main;

//Задание 1: создать консольное приложение “Учет книг в домашней библиотеке”.
//
//        Общие требования к заданию:
//
//        • Система учитывает книги как в электронном, так и в бумажном варианте. • Существующие роли: пользователь, администратор.
//        • Пользователь может просматривать книги в каталоге книг, осуществлять поиск книг в каталоге.
//        • Администратор может модифицировать каталог.
//        • *При добавлении описания книги в каталог оповещение о ней рассылается на e-mail всем пользователям
//        • **При просмотре каталога желательно реализовать постраничный просмотр
//        • ***Пользователь может предложить добавить книгу в библиотеку, переслав её администратору на e-mail.
//        • Каталог книг хранится в текстовом файле.
//        • Данные аутентификации пользователей хранятся в текстовом файле. Пароль не хранится в открытом виде
//
//        P.S. По умолчанию логин администратора: admin пароль: 0000

import HomeLibrary.library.Library;
import HomeLibrary.library.LibraryLogic;
import HomeLibrary.user.User;
import HomeLibrary.user.UserLogic;
import HomeLibrary.user.Users;
import HomeLibrary.view.Print;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        Users users = new Users();
        UserLogic.addUsersFromTXT("C:\\Users\\RGReeTy\\IdeaProjects\\ProjectOfBiblio\\src\\HomeLibrary\\user\\users.txt", users);
        Library library = new Library();
        LibraryLogic.addBooksFromTXT("C:\\Users\\RGReeTy\\IdeaProjects\\ProjectOfBiblio\\src\\HomeLibrary\\library\\Library.txt", library);

        boolean bool = false;
        do {
            User user = UserLogic.getUser();
            if (UserLogic.isCorrectUser(user, users)) {
                if (UserLogic.isAdmin(user)) {
                    Print.printTheMessage("Wake up Neo");
                    user = UserLogic.getUserWithEMail(user, users);
                    RunAsAdmin runAsAdmin = new RunAsAdmin();
                    runAsAdmin.runAsAdmin(users, library, user);
                    bool = true;
                } else {
                    user = UserLogic.getUserWithEMail(user, users);
                    RunAsUser runAsUser = new RunAsUser();
                    runAsUser.runAsUser(user, users, library);
                    bool = true;
                }
            } else {
                Print.printTheMessage("That user does not exist!");
            }
        } while (!bool);
    }
}