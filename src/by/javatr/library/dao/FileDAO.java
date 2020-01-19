package by.javatr.library.dao;

import by.javatr.library.bean.User;
import by.javatr.library.dao.exception.DAOException;

import java.io.FileNotFoundException;

public interface FileDAO {

    User[] loadDataFromFile(String address) throws DAOException, FileNotFoundException;


}
