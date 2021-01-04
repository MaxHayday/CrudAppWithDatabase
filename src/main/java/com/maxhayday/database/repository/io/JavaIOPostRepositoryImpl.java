package com.maxhayday.database.repository.io;

import com.maxhayday.database.model.Post;
import com.maxhayday.database.repository.PostRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.maxhayday.database.repository.io.JavaIOUserRepositoryImpl.*;


public class JavaIOPostRepositoryImpl implements PostRepository {
    @Override
    public Post getById(Long id) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;
        Post post = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            statement = connection.createStatement();
            String SQL = "select * from crud_app.posts where id LIKE " + id;
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                post = Post.builder().id(resultSet.getLong("id"))
                        .content(resultSet.getString("content"))
                        .created(resultSet.getTimestamp("created"))
                        .updated(resultSet.getTimestamp("updated"))
                        .user_id(resultSet.getLong("user_id"))
                        .build();
            }
            return post;
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public List<Post> getPostListOfUserId(Long id) throws SQLException, ClassNotFoundException {
        List<Post> postList = new ArrayList<>();
        Post post;
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            statement = connection.createStatement();
            String SQL = "select * from crud_app.posts where user_id LIKE " + id;

            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                post = Post.builder().id(resultSet.getLong("id"))
                        .content(resultSet.getString("content"))
                        .created(resultSet.getTimestamp("created"))
                        .updated(resultSet.getTimestamp("updated"))
                        .user_id(resultSet.getLong("user_id"))
                        .build();
                postList.add(post);
            }
            return postList;
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
    public Post save(Post post) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            String SQL = "INSERT INTO crud_app.posts " +
                    "(content, created, updated, user_id) " + "VALUES (?,?,?,?)";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, post.getContent());
            preparedStatement.setTimestamp(2, post.getCreated());
            preparedStatement.setTimestamp(3, null);
            preparedStatement.setLong(4, post.getUser_id() == null ? 0 : post.getUser_id());
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
    public Post update(Post post) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            String SQL = "UPDATE crud_app.posts SET content=?,updated=? where id=" + post.getId();
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, post.getContent());
            preparedStatement.setTimestamp(2, post.getUpdated());
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
    public void deleteById(Long id) throws  ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            statement = connection.createStatement();
            String SQL = "DELETE FROM crud_app.posts WHERE id=" + id;
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

    @Override
    public List<Post> getAll() throws ClassNotFoundException, SQLException {
        List<Post> postList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            statement = connection.createStatement();
            String SQL = "SELECT * FROM crud_app.posts";
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                Post post = Post.builder().id(resultSet.getLong("id"))
                        .content(resultSet.getString("content"))
                        .created(resultSet.getTimestamp("created"))
                        .updated(resultSet.getTimestamp("updated"))
                        .user_id(resultSet.getLong("user_id"))
                        .build();
                postList.add(post);
            }
            return postList;
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
    public void deleteByUserId(Long id) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            statement = connection.createStatement();
            String SQL = "DELETE FROM crud_app.posts WHERE user_id=" + id;
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
