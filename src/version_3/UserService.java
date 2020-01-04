package version_3;

import java.util.List;
import java.util.regex.Pattern;

public class UserService {

    public static boolean checkFirstName(String firstName) {

        if (firstName == null) {
            return false;
        }

        if (Pattern.matches("[а-яА-Яa-zA-Z]+", firstName)) {
            return true;
        } else {
            return false;
        }


    }

    public static boolean checkEmail(String email) {

        if (email == null) {
            return false;
        }

        if (Pattern.matches("^[_A-Za-z0-9]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z]{2,})$", email)) {

            return true;

        } else {
            return false;
        }


    }

    public static boolean checkLastName(String lastName) {
        if (lastName == null) {
            return false;
        }

        if (Pattern.matches("[а-яА-Яa-zA-Z]+", lastName)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkLogin(String login) {

        if (login == null) {
            return false;
        }

        if (Pattern.matches("[a-zA-Z0-9]+", login)) {
            return true;

        } else {
            return false;
        }


    }

    public static boolean hasLoginInList(String login, List<User> users){

        if (login == null) {
            return false;
        }

        if (users==null){
            return false;
        }

        for (User user:users) {

            if (user.getLogin().equals(login)){
                return true;
            }

        }
        return false;

    }

    public static boolean checkPassword(String password) {
        if (password == null) {
            return false;
        }

        if (password.length() >= 6) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkNumberPhone(String numberPhone) {

        if (numberPhone == null) {
            return false;
        }

        if (Pattern.matches("^375(33|44|29|25)\\d{7}", numberPhone)) {
            return true;

        } else {
            return false;
        }


    }

    public static boolean checkRole(String role) {
        if (role != null) {
            return true;

        }
        return false;
    }

}
