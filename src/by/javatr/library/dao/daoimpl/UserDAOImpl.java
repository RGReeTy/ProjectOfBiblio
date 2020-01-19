package by.javatr.library.dao.daoimpl;

import by.javatr.library.bean.User;
import by.javatr.library.dao.FileDAO;
import by.javatr.library.dao.UserDAO;
import by.javatr.library.dao.exception.DAOException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UserDAOImpl implements UserDAO, FileDAO {
    User[] users = null;

    {
        try {
            users = loadDataFromFile("src\\by\\javatr\\library\\resource\\user.txt");
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean signIn(String login, String password) throws DAOException {
        for (User user : users) {
            if (user.getUserName().equalsIgnoreCase(login) & user.getUserPassword().equalsIgnoreCase(password))
                return true;
        }
        return false;
    }

    @Override
    public User[] loadDataFromFile(String address) throws DAOException, FileNotFoundException {

        User[] users = new User[0];

        Scanner scanner = new Scanner(new File(address), "UTF-8");
        String usersInString = scanner.useDelimiter("\\A").next();

        Pattern pattern = Pattern.compile("([а-яА-яA-Za-z0-9]+)\\s([а-яА-яA-Za-z0-9]+)");
        Matcher matcher = pattern.matcher(usersInString);

        while (matcher.find()) {
            users = Arrays.copyOf(users, users.length + 1);
            users[users.length - 1] = new User(matcher.group(1), matcher.group(2));
        }
        return users;
    }

    @Override
    public boolean registration(User user) throws DAOException {
        return false;
    }

    public User[] getUsers() {
        return users;
    }
}
