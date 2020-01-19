package by.javatr.library.dao;

import by.javatr.library.bean.User;
import by.javatr.library.dao.exception.DAOException;

public interface UserDAO {

    boolean signIn(String login, String password) throws DAOException;

    boolean registration(User user) throws DAOException;


}
