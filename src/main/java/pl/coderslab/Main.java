package pl.coderslab;

import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDao;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();

        User user1 = new User();

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
//         User[] users = userDao.findAll();
//        System.out.println(users[3].getId());


        User user3 = userDao.read(8);
        System.out.println(user3.getUserName());
    }

    public static int getUserId() {
        System.out.println("Podaj id użytkownika: ");
        Scanner scan = new Scanner(System.in);
        int userID;
        while (true) {
            if (scan.hasNextInt()) {
                userID = scan.nextInt();
                if (userID > 0) {
                    return userID;
                }
            } else {
                scan.next();
            }
            System.out.println("Podaj prawidłową dodatnią liczbę całkowitą");
        }
    }
    public static String getNewPassword(){
        System.out.println("Podaj nowe hasło: ");
        Scanner scan = new Scanner(System.in);
        return scan.next();

    }

}
