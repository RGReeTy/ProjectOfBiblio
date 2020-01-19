package by.javatr.library.service;

import by.javatr.library.bean.User;
import by.javatr.library.dao.daoimpl.UserDAOImpl;
import by.javatr.library.dao.exception.DAOException;
import by.javatr.library.service.exception.ServiceException;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class ClientService {
    UserDAOImpl userDAO = new UserDAOImpl();
    private User[] users;

    public ClientService() throws FileNotFoundException, DAOException {
        users = userDAO.getUsers();
    }

    public boolean signIn(String login, String password) throws ServiceException {
        try {
            return userDAO.signIn(login, password);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addUser(User user) {
        users = Arrays.copyOf(users, users.length + 1);
        users[users.length - 1] = user;
    }

    public User[] getUsers() {
        return users;
    }

}
