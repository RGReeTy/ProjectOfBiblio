package by.javatr.library.service.validation;

import by.javatr.library.bean.User;

public class Validation {

    public static boolean checkTheUserOnAuth(String login, String password, User user) {
        return user.getUserName().equalsIgnoreCase(login)
                & user.getUserPassword().equalsIgnoreCase(cryptThePassword(password));
    }

    public static String cryptThePassword(String password) {
        String cryptedWord = "";
        char[] symbols = password.toCharArray();
        for (char ch : symbols) {
            cryptedWord += (ch + 13);
        }
        return cryptedWord;
    }
}
