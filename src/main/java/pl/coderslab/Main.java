package pl.coderslab;

import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDao;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
/*
        User user = new User();
        user.setEmail("example_email@ee.com");
        user.setUserName("gamer69");
        user.setPassword("passsss");

        User user2 = UserDao.read(3);
        System.out.println("ID: " + user2.getId() +", username: " + user2.getUserName() +", email: " + user2.getEmail() + ", zhashowane hasło: " + user2.getPassword());

        user2.getId();
        user2.setUserName("zaktualizowanyUser");
        user2.setEmail("zaktualizowanyeamil@mail.com");
        user2.setPassword("cokolwiek");

        UserDao.update(user2);

        UserDao.read(3);
        System.out.println("ID: " + user2.getId() +", username: " + user2.getUserName() +", email: " + user2.getEmail() + ", zhashowane hasło: " + user2.getPassword());
*/

//        UserDao.delete(7);
         User[] users = UserDao.findAll();
        System.out.println(users[3].getUserName());
    }
}
