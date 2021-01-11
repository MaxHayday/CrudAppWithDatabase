package com.maxhayday.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;

public class ConnectionUtils {
    private static final Path properties = Paths.get("src/main/resources/db/mysql.properties");
    public static Connection connection;
    public static Statement statement;
    public static PreparedStatement preparedStatement;
    private ArrayList<String> dbProperties = new ArrayList<>();

    public ConnectionUtils() throws IOException, ClassNotFoundException, SQLException {
        readProperties();
        Class.forName(dbProperties.get(1));
        connection = DriverManager.getConnection(dbProperties.get(0), dbProperties.get(2), dbProperties.get(3));
    }

    private void readProperties() throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(properties)) {
            String[] str;
            while (reader.ready()) {
                str = reader.readLine().split("=");
                dbProperties.add(str.length < 2 ? "" : str[1]);
            }
        }
    }

    public static Statement getStatement() throws SQLException {
        statement = connection.createStatement();
        return statement;
    }


    public static PreparedStatement getPreparedStatement(String SQL) throws SQLException {
        preparedStatement = connection.prepareStatement(SQL);
        return preparedStatement;
    }

    public static void closeConnection() throws SQLException {
        if (statement != null) {
            statement.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}
