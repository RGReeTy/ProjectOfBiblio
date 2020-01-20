package by.javatr.library.bean;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

    private String userName;
    private String userPassword;
    private boolean isAdmin = false;

    public User() {
    }

    public User(String userName, String userPassword, boolean isAdmin) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.isAdmin = isAdmin;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isAdmin == user.isAdmin &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(userPassword, user.userPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, userPassword, isAdmin);
    }

    @Override
    public String toString() {
        return "User {" +
                "userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
