package org.example;

import java.sql.*;
import java.util.*;

public class DbControl {
    private static final String CON_STR = "jdbc:sqlite:users.sqlite";

    private static DbControl instance = null;

    public static synchronized DbControl getInstance() throws SQLException {
        if (instance == null)
            instance = new DbControl();
        return instance;
    }

    private Connection connection;

    public DbControl() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ;
        this.connection = DriverManager.getConnection(CON_STR);
    }

    public List<User> getAllUsers() {
        try (Statement statement = this.connection.createStatement()) {
            List<User> users = new ArrayList<User>();
            ResultSet resultSet = statement.executeQuery("SELECT id, name, rubles, dollars, euros, state FROM users");
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("rubles"),
                        resultSet.getDouble("dollars"),
                        resultSet.getDouble("euros"),
                        resultSet.getInt("state")));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public int getMaxId() throws SQLException {
        return Integer.parseInt(this.connection.createStatement().executeQuery("SELECT MAX(id) FROM users").toString());
    }

    public User getUser(int id) {
        User user = null;
        try (PreparedStatement statement = this.connection.prepareStatement("SELECT id, name, rubles, dollars, euros, state FROM users WHERE id="+ id)){
            ResultSet rs = statement.executeQuery();
            user = new User();
            user.setId(rs.getInt(1));
            user.setName(rs.getString(2));
            user.setRubles(rs.getDouble(3));
            user.setDollars(rs.getDouble(4));
            user.setEuros(rs.getDouble(5));
            user.setState(rs.getInt(6));


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public int editUser(User user, int id){
        var result = 0;
        try (PreparedStatement statement = this.connection.prepareStatement("UPDATE users SET rubles="+user.getRubles() + ", dollars="+user.getDollars()+", euros="+ user.getEuros() +" WHERE id="+user.getId())){
            result = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void addUser(User user) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO users(`id`, `name`, `rubles`, `dollars`, `euros`, `state`) VALUES(?, ?, ?, ?, ?, ?)")) {
            statement.setObject(1, user.getId());
            statement.setObject(2, user.getName());
            statement.setObject(3, user.getRubles());
            statement.setObject(4, user.getDollars());
            statement.setObject(5, user.getEuros());
            statement.setObject(6, user.getState());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
