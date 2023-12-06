package com.github.DanilFilippov123;

import org.sqlite.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlLightHandler {
    private static final String DEFAULT_DB_FILENAME = "db.db";

    private static SqlLightHandler instance;

    private final Connection connection;

    public static synchronized SqlLightHandler getInstance() {
        if (instance == null) {
            try {
                instance = new SqlLightHandler();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    private SqlLightHandler() throws SQLException {
        String filename = String.valueOf(ClassLoader.getSystemResource(SqlLightHandler.DEFAULT_DB_FILENAME));
        DriverManager.registerDriver(new JDBC());
        this.connection = DriverManager.getConnection("jdbc:sqlite:" + filename);
    }

    public void insertAllSportObjects(List<SportObject> sportObjects) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO SportObjects(`id`, `name`, `subject`, 'address', 'date') " +
                "VALUES(?, ?, ?, ?, ?)")) {

            for(SportObject object : sportObjects) {
                statement.setInt(1, object.id);
                statement.setString(2, object.name);
                statement.setString(3, object.subject);
                statement.setString(4, object.address);
                statement.setDate(5, Date.valueOf(object.date));
                statement.execute();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<SportObject> selectAllSportObjects() {
        List<SportObject> sportObjects = new ArrayList<>();
        try (Statement statement = this.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT id, name, subject, address, date FROM SportObjects");
            while (resultSet.next()) {
                sportObjects.add(new SportObject(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("subject"),
                        resultSet.getString("address"),
                        resultSet.getDate("date").toLocalDate()));
            }
            // Возвращаем наш список
            return sportObjects;

        } catch (SQLException e) {
            return List.of();
        }
    }

    public void truncateTable() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM SportObjects");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}