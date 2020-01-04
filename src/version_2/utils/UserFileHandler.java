package version_2.utils;



import version_2.entity.user.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserFileHandler {

    private static String csvFile = "C:/Users/RGReeTy/IdeaProjects/GrowMyJava/src/com/grow_my_java/module_6/task01/data/users.csv";

    /**
     * Load users ti the list from csv file.
     *
     * @return the list of users.
     */
    public static List<User> loadUsers() {

        String line;
        String splitBy = ",";

        List<User> users = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                String[] data = line.split(splitBy);

                User user = new UserBuilder(data[5], data[6]).withName(data[0])
                        .withLastname(data[1])
                        .setSex(Sex.valueOf(data[2].toUpperCase()))
                        .setRole(Role.valueOf(data[3].toUpperCase()))
                        .setEmail(new Email(data[4]))
                        .build();
                users.add(user);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * Save user list in the csv file in the next format:
     * "name,lastname,sex,role,email,username,securedPassword,salt".
     */
    public static void saveUserList(List<User> users) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile))) {

            for (User user : users) {
                StringBuilder sb = new StringBuilder();
                sb.append(user.getName());
                sb.append(",");
                sb.append(user.getLastname());
                sb.append(",");
                sb.append(user.getSex().name().toLowerCase());
                sb.append(",");
                sb.append(user.getRole().name().toLowerCase());
                sb.append(",");
                sb.append(user.getEmail());
                sb.append(",");
                sb.append(user.getUsername());
                sb.append(",");
                sb.append(user.getPassword());

                bw.write(sb.toString());
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
