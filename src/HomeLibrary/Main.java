package HomeLibrary;

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

import HomeLibrary.entity.library.Library;
import HomeLibrary.service.LibraryLogic;
import HomeLibrary.service.runner.RunAsAdmin;
import HomeLibrary.service.runner.RunAsUser;
import HomeLibrary.entity.user.User;
import HomeLibrary.service.UserLogic;
import HomeLibrary.entity.user.Users;
import HomeLibrary.view.Print;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        Users users = new Users();
        //new FileReader("src\\PACKAGE_NAME\\FILENAME");
        UserLogic.addUsersFromTXT("src\\HomeLibrary\\file\\users.txt", users);
        Library library = new Library();
        LibraryLogic.addBooksFromTXT("src\\HomeLibrary\\file\\Library.txt", library);

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