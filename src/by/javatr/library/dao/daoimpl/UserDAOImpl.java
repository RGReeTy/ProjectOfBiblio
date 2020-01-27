package by.javatr.library.dao.daoimpl;

import by.javatr.library.bean.User;
import by.javatr.library.dao.FileDAO;
import by.javatr.library.dao.UserDAO;
import by.javatr.library.dao.exception.DAOException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.javatr.library.service.validation.Validation.checkTheUserOnAuth;


public class UserDAOImpl implements UserDAO, FileDAO {
    {
        try {
            loadDataFromFile("src\\by\\javatr\\library\\resource\\user\\users.txt");
        } catch (DAOException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static final Map<Integer, User> clientList = new HashMap<Integer, User>();
    private static int id = 0;
    private User currentUser = new User();

    @Override
    public boolean signIn(String login, String password) throws DAOException {
        for (Map.Entry<Integer, User> entry : clientList.entrySet()) {
            if (checkTheUserOnAuth(login, password, entry.getValue())) {
                currentUser.setUserName(entry.getValue().getUserName());
                currentUser.setUserPassword(entry.getValue().getUserPassword());
                currentUser.setAdmin(entry.getValue().isAdmin());
                return true;
            }
        }
        return false;
    }

    @Override
    public void loadDataFromFile(String address) throws DAOException, FileNotFoundException {

        Scanner scanner = new Scanner(new File(address), "UTF-8");
        String usersInString = scanner.useDelimiter("\\A").next();

        Pattern pattern = Pattern.compile("([а-яА-яA-Za-z0-9]+)\\s([а-яА-яA-Za-z0-9]+)\\s(true|false)");
        Matcher matcher = pattern.matcher(usersInString);

        while (matcher.find()) {
            clientList.put(++id, new User(matcher.group(1), matcher.group(2), Boolean.parseBoolean(matcher.group(3))));
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void addUser(String login, String password) {
        clientList.put(++id, new User(login, password, false));
        try (FileWriter writer = new FileWriter("src\\by\\javatr\\library\\resource\\user\\users.txt", true)) {
            writer.append(System.lineSeparator());
            writer.write(login + " " + password + " " + "false");
            writer.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean registration(User user) throws DAOException {
        //вставить валидацию, если юзер уже существует - вернуть false, нет - зарегистрировать + true
        return false;
    }
}
