package pl.coderslab.entity;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.DbUtil;

import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;

public class UserDao {
    private static final String CREATE_USER_QUERY = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
    private static final String READ_USER_QUERY = "SELECT * FROM users WHERE ID = ?";
    private static final String UPDATE_USER_QUERY = "update users set username = ?,email = ?, password = ? where id = ?";
    private static final String DELETE_USER_QUERY = "delete from users where id = ?";

    public static void create_user(User user) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement preStmt = conn.prepareStatement(CREATE_USER_QUERY, PreparedStatement.RETURN_GENERATED_KEYS)) {

            preStmt.setString(1, user.getUserName());

            preStmt.setString(2, user.getEmail());

            preStmt.setString(3, hashPassword(user.getPassword()));
            preStmt.executeUpdate();

            ResultSet rs = preStmt.getGeneratedKeys();
            if (rs.next()) {
                long id = rs.getLong(1);
                System.out.println("Inserted ID: " + id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static String hashPassword(String password) {

        return BCrypt.hashpw(password, BCrypt.gensalt());

    }

    public static User read(int userId) {
        User readedUser = new User();
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement prepStmt = conn.prepareStatement(READ_USER_QUERY)) {
            prepStmt.setInt(1, userId);
            prepStmt.executeQuery();
            ResultSet rs = prepStmt.getResultSet();
            if (rs.next()) {

                readedUser.setId(rs.getInt("id"));
                readedUser.setUserName(rs.getString("username"));
                readedUser.setPassword(hashPassword(rs.getString("password")));
                readedUser.setEmail(rs.getString("email"));
            } else {
                readedUser.setUserName(null);
                readedUser.setEmail(null);
                readedUser.setPassword(null);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return readedUser;
    }

    public static void update(User user) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement prep = conn.prepareStatement(UPDATE_USER_QUERY)) {
            prep.setInt(4, user.getId());
            prep.setString(1, user.getUserName());
            prep.setString(2, user.getEmail());
            prep.setString(3, hashPassword(user.getPassword()));
            prep.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public static void delete(int userId) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement prep = conn.prepareStatement(DELETE_USER_QUERY)) {

            prep.setInt(1, userId);
            prep.executeUpdate();

            System.out.println("User o id: " + userId + ", został usunięty.");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static User[] findAll() {
        User[] users = new User[0];

        try (Connection conn = DbUtil.getConnection();
             PreparedStatement st = conn.prepareStatement("SELECT * FROM users")) {
            ResultSet rs = st.executeQuery();
/*            PreparedStatement prep = conn.prepareStatement("SELECT COUNT(*) AS rowcount FROM users");
            ResultSet row_number = prep.executeQuery();
            row_number.next();
            int rowNumber = row_number.getInt("rowcount");
            System.out.println("row count " + rowNumber);*/
            while (rs.next()) {
                User templateUser = new User();
                templateUser.setUserName(rs.getString("username"));
                templateUser.setEmail(rs.getString("email"));
                templateUser.setId(rs.getInt("id"));
                templateUser.setPassword(rs.getString("password"));
                users = addToArray(templateUser, users);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    private static User[] addToArray(User u, User[] users) {
        User[] tmpUsers = Arrays.copyOf(users, users.length + 1);

        tmpUsers[users.length] = u;
        return tmpUsers;
    }
}
