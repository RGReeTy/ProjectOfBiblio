package version_3;

import java.util.Objects;

import static version_3.UserService.*;

public class User {

    private static int GENERATEID = 0;

    private int id = GENERATEID++;
    private String firstName;
    private String lastName;
    private String login;
    private String email;
    private String password;
    private long numberPhone;
    private Role role;

    public User(String firstName, String lastName, String login, String email, String password, String numberPhone, String role) throws IllegalArgumentException {

        if (checkFirstName(firstName)) {
            this.firstName = firstName;
        } else {
            throw new IllegalArgumentException("First name is incorrect");
        }

        if (checkLastName(lastName)) {
            this.lastName = lastName;
        } else {
            throw new IllegalArgumentException("Last name is incorrect");
        }

        if (checkLogin(login)) {
            this.login = login;
        } else {
            throw new IllegalArgumentException("Login is incorrect");
        }


        if (checkEmail(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Email is incorrect");
        }

        if (checkPassword(password)) {
            this.password = password;
        } else {
            throw new IllegalArgumentException("Password is incorrect");
        }

        if (checkNumberPhone(numberPhone)) {
            this.numberPhone = Long.parseLong(numberPhone);
        } else {
            throw new IllegalArgumentException("Number phone is incorrect");
        }

        if (checkRole(role)) {

            if (Role.Admin.toString().equals(role)) {
                this.role = Role.Admin;
            } else {
                this.role = Role.User;
            }

        } else {
            throw new IllegalArgumentException("Role is incorrect");
        }

    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public long getNumberPhone() {
        return numberPhone;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return numberPhone == user.numberPhone &&
                firstName.equals(user.firstName) &&
                lastName.equals(user.lastName) &&
                login.equals(user.login) &&
                email.equals(user.email) &&
                password.equals(user.password) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, login, email, password, numberPhone, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", numberPhone=" + numberPhone +
                ", role=" + role +
                '}';
    }

}
