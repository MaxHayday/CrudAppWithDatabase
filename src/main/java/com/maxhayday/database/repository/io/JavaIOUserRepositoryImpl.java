package com.maxhayday.database.repository.io;

import java.sql.*;

import com.maxhayday.database.model.Post;
import com.maxhayday.database.model.Region;
import com.maxhayday.database.model.Role;
import com.maxhayday.database.model.User;
import com.maxhayday.database.repository.PostRepository;
import com.maxhayday.database.repository.RegionRepository;
import com.maxhayday.database.repository.UserRepository;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class JavaIOUserRepositoryImpl implements UserRepository {
    public static final String DATABASE_URL = "jdbc:mysql://localhost:3306/crud_app";
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    public static final String USER = "root";
    public static final String PASSWORD = "";

    private PostRepository postRepository;
    private RegionRepository regionRepository;

    public JavaIOUserRepositoryImpl() {
        postRepository = new JavaIOPostRepositoryImpl();
        regionRepository = new JavaIORegionRepositoryImpl();
    }

    @Override
    public User getById(Long id) throws IOException, ParseException, ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            String SQL = "select * from crud_app.users where id=" + id;
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                User user = User.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .lastName(resultSet.getString("surname"))
                        .posts(postRepository.getPostListOfUserId(resultSet.getLong("id")))
                        .region(regionRepository.getById(resultSet.getLong("region_id")))
                        .role(Role.valueOf(resultSet.getString("role")))
                        .build();
                return user;
            }
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return null;
    }

    @Override
    public User save(User user) throws ClassNotFoundException, SQLException, IOException, ParseException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            String SQL = "INSERT INTO crud_app.users " +
                    "(name,surname,role,region_id) " +
                    "VALUES (?,?,?,?)";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getRole().name());
            preparedStatement.setLong(4, user.getRegion().getId());
            preparedStatement.executeUpdate();
            SQL = "select * from users";
            Statement statement = connection.prepareStatement(SQL);
            ResultSet resultSet = statement.executeQuery(SQL);
            resultSet.last();
            user = User.builder()
                    .id(resultSet.getLong("id"))
                    .name(resultSet.getString("name"))
                    .lastName(resultSet.getString("surname"))
                    .posts(null)
                    .region(regionRepository.getById(resultSet.getLong("region_id")))
                    .role(Role.valueOf(resultSet.getString("role")))
                    .build();
            return user;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public User update(User user) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            String SQL = "UPDATE crud_app.users SET name=?,surname=?,role=? where id=" + user.getId();
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getRole().name());
            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return null;
    }

    @Override
    public List<User> getAll() throws IOException, ParseException, ClassNotFoundException, SQLException {
        List<User> list = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            statement = connection.createStatement();
            String SQL = "SELECT * FROM crud_app.users";
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                User user = User.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .lastName(resultSet.getString("surname"))
                        .posts(postRepository.getPostListOfUserId(resultSet.getLong("id")))
                        .region(regionRepository.getById(resultSet.getLong("region_id")))
                        .role(Role.valueOf(resultSet.getString("role")))
                        .build();
                list.add(user);
            }
            return list;
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void deleteById(Long id) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            statement = connection.createStatement();
            String SQL = "DELETE FROM crud_app.users WHERE id=" + id;
            statement.executeUpdate(SQL);
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

}
