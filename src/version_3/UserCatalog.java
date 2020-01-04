package version_3;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserCatalog {

    private String fileName = "sourceUser.txt";
    private List<User> users = new ArrayList<>();
    private ReadWriteFiles readWriteFiles;

    public UserCatalog() throws IOException {

        createUsers();

    }


    private void createUsers() throws IOException {
        List<String> lines;
        User user;

        readWriteFiles = new ReadWriteFiles(fileName);

        lines = readWriteFiles.read();

        Iterator<String> stringIterator = lines.iterator();


        while (stringIterator.hasNext()) {
            try {

                String firstName = stringIterator.next();

                if (firstName.equals("")) {
                    break;
                }
                String lastName = stringIterator.next();
                String login = stringIterator.next();
                String email = stringIterator.next();
                String password = encrypt(stringIterator.next());
                String numberPhone = stringIterator.next();
                String role = Role.Admin.toString().equals(stringIterator.next()) ? "Admin" : "User";

                if (UserService.hasLoginInList(login,users)) {
                    break;
                }


                user = new User(firstName, lastName, login, email, password, numberPhone, role);

                users.add(user);

            } catch (NumberFormatException e) {
                System.err.println("Incorrect data the user");
            }
        }

        save();
    }

    private String encrypt(String s) {

        MessageDigest m = null;

        try {

            m = MessageDigest.getInstance("MD5");

        } catch (NoSuchAlgorithmException e) {

            System.err.println("Encryption error");

        }

        m.reset();

        try {

            m.update(s.getBytes("utf-16"));

        } catch (UnsupportedEncodingException e) {

            System.err.println("Unsupported operation with string");

        }

        return new BigInteger(1, m.digest()).toString(16);

    }


    public boolean add(String firstName, String lastName, String login, String email, String password, String numberPhone, String role) {


        if (firstName != null && lastName != null &&
                login != null && email != null &&
                password != null && role != null) {

            if (UserService.hasLoginInList(login,users)){
                return false;
            }

            User user = new User(firstName, lastName, login, email,
                    encrypt(password), numberPhone,
                    role.equals("Admin") ? Role.Admin.toString() : Role.User.toString());

            this.add(user);
            return true;

        }

        return false;
    }

    private boolean add(User user) {

        if (!users.contains(user)) {
            users.add(user);
            save();
            return true;
        }
        return false;

    }

    public boolean remove(int userId) {

        for (User user : users) {

            if (user.getId() == userId) {

                users.remove(user);
                return true;

            }

        }
        return false;


    }

    public void save() {

        ReadWriteFiles readWriteFiles = new ReadWriteFiles("users.txt");
        List<String> lines = new ArrayList<>();


        for (User user : users) {

            lines.add(user.getFirstName());
            lines.add(user.getLastName());
            lines.add(user.getLogin());
            lines.add(user.getEmail());
            lines.add(user.getPassword());
            lines.add(Long.toString(user.getNumberPhone()));
            lines.add(user.getRole().toString());


        }

        try {
            readWriteFiles.write(lines);

        } catch (IOException e) {
            System.err.println("Error while saving to file");
        }

    }

    public int size() {
        return users.size();
    }

    public List<User> getUsers() {
        return users;
    }

    public User searchByLoginAndPassword(String login, String password) {

        String hashPassword = encrypt(password);

        for (User user : users) {

            if (user.getLogin().equals(login)) {

                if (user.getPassword().equals(hashPassword)) {
                    return user;
                }

            }

        }
        return null;

    }

//    public boolean checkSingIn(String login, String password) {
//
//        String hashPassword = encrypt(password);
//
//        for (User user : users) {
//
//            if (user.getLogin().equals(login)) {
//
//                if (user.getPassword().equals(hashPassword)) {
//                    return true;
//                }
//
//            }
//
//        }
//        return false;
//
//    }
//
//    public boolean isAdmin(String login, String password) {
//
//        String hashPassword = encrypt(password);
//
//        for (User user : users) {
//
//            if (user.getLogin().equals(login)) {
//
//                if (user.getPassword().equals(hashPassword)) {
//
//                    if (user.getRole() == Role.Admin) {
//                        return true;
//                    }
//
//                }
//
//            }
//
//        }
//        return false;
//
//    }


}
