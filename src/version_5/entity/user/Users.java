package version_5.entity.user;

import java.util.Arrays;

public class Users {
    private User[] users = new User[0];

    public void addUser(User user) {
        users = Arrays.copyOf(users, users.length + 1);
        users[users.length - 1] = user;
    }

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }
}
