package com.maxhayday.database.repository.io;

import com.maxhayday.database.model.Region;
import com.maxhayday.database.repository.RegionRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.maxhayday.database.repository.io.JavaIOUserRepositoryImpl.*;

public class JavaIORegionRepositoryImpl implements RegionRepository {
    private Region region;

    @Override
    public Region getById(Long id) throws  ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            statement = connection.createStatement();
            String SQL = "select * from crud_app.regions where id LIKE " + id;
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                region = Region.builder().id(resultSet.getLong("id"))
                        .name(resultSet.getString("name")).build();
            }
            return region;
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
    public Region save(Region region) throws  SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            String SQL = "INSERT INTO crud_app.regions " + "(name) " + "VALUES (?)";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, region.getName());
            preparedStatement.executeUpdate();

            Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            SQL = "select * from regions";
            ResultSet resultSet = statement.executeQuery(SQL);
            resultSet.last();

            region = Region.builder().id(resultSet.getLong("id")).name(resultSet.getString("name")).build();
            return region;
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
    public Region update(Region region) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            String SQL = "UPDATE crud_app.regions SET name=? where id=" + region.getId();
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, region.getName());
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
    public List<Region> getAll() throws  ClassNotFoundException, SQLException {
        List<Region> regionList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            statement = connection.createStatement();
            String SQL = "SELECT * FROM crud_app.regions";
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                Region region = Region.builder().id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .build();
                regionList.add(region);
            }
            return regionList;
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
            String SQL = "DELETE FROM crud_app.regions WHERE id=" + id;
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
