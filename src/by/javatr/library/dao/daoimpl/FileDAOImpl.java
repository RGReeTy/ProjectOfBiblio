package by.javatr.library.dao.daoimpl;

import by.javatr.library.bean.User;
import by.javatr.library.dao.FileDAO;
import by.javatr.library.dao.exception.DAOException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileDAOImpl implements FileDAO {

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
}
