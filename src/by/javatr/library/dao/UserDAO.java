package by.javatr.library.dao;

import by.javatr.library.bean.User;
import by.javatr.library.dao.exception.DAOException;

public interface UserDAO {

    void signIn(String login, String password) throws DAOException;

    void registration(User user) throws DAOException;
}
