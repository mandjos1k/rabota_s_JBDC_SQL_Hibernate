package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    private final Connection conn = getConn();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        String sql = "CREATE TABLE USERS " +
                "(id bigint NOT NULL AUTO_INCREMENT, " +
                " name text NOT NULL, " +
                " lastName text NOT NULL, " +
                " age tinyint, " +
                " PRIMARY KEY ( id ))";
        try {
            Statement statement = conn.createStatement();
            statement.execute(sql);
            System.out.println("Table created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE USERS";

        try {
            Statement statement = conn.createStatement();
            statement.execute(sql);
            System.out.println("Table dropped");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO USERS (name, lastName, age) VALUES (?, ?, ?)";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            System.out.println("User saved");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void removeUserById(long id) {
        String sql = "DELETE FROM USERS WHERE id = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);
            statement.execute();
            System.out.println("User removed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM USERS";
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("Lastname"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
                System.out.println(user.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;

    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE USERS";
        try {
            Statement statement = conn.createStatement();

            statement.execute(sql);
            System.out.println("Table cleaned");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
