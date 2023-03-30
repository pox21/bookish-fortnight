package user;

import parser.GsonParser;
import parser.GsonToJson;

import java.util.List;

public class UsersControl {
    GsonToJson gsonToJson = new GsonToJson();
    private final String pathToUsers = "src/main/resources/users.json";

    private List<User> users;

    private List<User> getUsers() {
        return GsonParser.parseUsers(pathToUsers);
    }

    public boolean isThereUser(User user) {
        users = getUsers();
        if (users == null || users.size() == 0) return false;

        for (User u : users) {
            if (u.getEmail().equals(user.getEmail())) {
                return true;
            }
        }
        return false;
    }

    public boolean addUser(User user) {
        if (isThereUser(user)) {
            System.out.println("Такой пользователь уже существует");
            return false;
        } else {
            users.add(user);
            gsonToJson.addUsersToJson(users, pathToUsers);
            return true;
        }
    }

    public User getUserByEmail(String email) {
        users = getUsers();
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    public User login(String email, String pass) {
        users = getUsers();
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(pass)) {
                return user;
            }
        }
        return null;
    }


}
