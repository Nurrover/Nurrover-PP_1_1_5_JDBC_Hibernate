package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Nurlan", "Safarov", (byte) 20);
        userService.saveUser("Kirill", "Savin", (byte) 13);
        userService.saveUser("Vladik", "Pavlov", (byte) 19);
        userService.saveUser("Petr", "Sidorov", (byte) 14);
        for (User user: userService.getAllUsers()) {
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
