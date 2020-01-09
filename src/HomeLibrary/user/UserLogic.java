package HomeLibrary.user;

import HomeLibrary.view.Print;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserLogic {

    public void addUsersFromTXT(String address, Users users) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File(address), "UTF-8");
        String usersInString = scanner.useDelimiter("\\A").next();

        Pattern pattern = Pattern.compile("([а-яА-яA-Za-z0-9]+)\\s([а-яА-яA-Za-z0-9]+)\\s([a-zA-Z._\\-]+@[a-z]+\\.[a-z]+)");
        Matcher matcher = pattern.matcher(usersInString);

        while (matcher.find()) {
            users.addUser(new User(matcher.group(1), matcher.group(2), matcher.group(3)));
        }
    }

    public boolean isCorrectUser(User user, Users users) {
        for (User userNew : users.getUsers()) {
            if (userNew.getUserName().equals(user.getUserName()) && userNew.getUserPassword().equals(user.getUserPassword())) {
                return true;
            }
        }
        return false;
    }

    public boolean isAdmin(User user) {

        return user.getUserName().equals("admin");
    }

    public User getUser() {
        User user = new User();
        Scanner scanner = new Scanner(System.in);
        Print.printTheMessage("Enter login");
        user.setUserName(scanner.next());
        Print.printTheMessage("Enter password");
        user.setUserPassword(scanner.next());
        return user;
    }

    public User getUserWithEMail(User user, Users users) {
        for (User userNew : users.getUsers()) {
            if (userNew.getUserName().equals(user.getUserName()) && userNew.getUserPassword().equals(user.getUserPassword())) {
                return userNew;
            }
        }
        return null;
    }
}
