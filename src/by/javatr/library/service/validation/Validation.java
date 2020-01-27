package by.javatr.library.service.validation;

import by.javatr.library.bean.User;

public class Validation {

    public static boolean checkTheUserOnAuth(String login, String password, User user) {
        return user.getUserName().equalsIgnoreCase(login) & user.getUserPassword().equalsIgnoreCase(password);
    }
}
