package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Misha", null, (byte) 20);
        System.out.println("User с именем - " + userService.getUserName() + " добавлен в базу данных");
        userService.saveUser("Petya", "Petrov", (byte) 25);
        System.out.println("User с именем - " + userService.getUserName() + " добавлен в базу данных");
        userService.saveUser("Kirill", "Zuchkin", (byte) 31);
        System.out.println("User с именем - " + userService.getUserName() + " добавлен в базу данных");
        userService.saveUser("Vasya", "Pupkin", (byte) 38);
        System.out.println("User с именем - " + userService.getUserName() + " добавлен в базу данных");

        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();

        Util.closeConnection();
    }
}
