package com.maxhayday.database.repository.jdbc;

import java.sql.*;

import com.maxhayday.database.ConnectionUtils;
import com.maxhayday.database.SqlQueries;
import com.maxhayday.database.model.Post;
import com.maxhayday.database.model.Region;
import com.maxhayday.database.model.Role;
import com.maxhayday.database.model.User;
import com.maxhayday.database.repository.PostRepository;
import com.maxhayday.database.repository.RegionRepository;
import com.maxhayday.database.repository.UserRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.maxhayday.database.ConnectionUtils.preparedStatement;

public class JdbcUserRepositoryImpl implements UserRepository {

    private PostRepository postRepository;
    private RegionRepository regionRepository;

    public JdbcUserRepositoryImpl() throws SQLException, IOException, ClassNotFoundException {
        ConnectionUtils connectionUtils = new ConnectionUtils();
        postRepository = new JdbcPostRepositoryImpl();
        regionRepository = new JdbcRegionRepositoryImpl();
    }

    @Override
    public User getById(Long id) throws SQLException {
        User user = null;
        preparedStatement = ConnectionUtils.connection.prepareStatement(SqlQueries.GET_USER_BY_ID.getSQL());
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Post> postList = new ArrayList<>();
        while (resultSet.next()) {
            postList.add(Post.builder().id(resultSet.getLong("posts.id")).content(resultSet.getString("posts.content"))
                    .created(resultSet.getTimestamp("posts.created")).updated(resultSet.getTimestamp("posts.updated")).user_id(resultSet.getLong("posts.user_id")).build());
            Region region = Region.builder().id(resultSet.getLong("regions.id")).name(resultSet.getString("regions.name")).build();
            user = User.builder()
                    .id(resultSet.getLong("users.id"))
                    .name(resultSet.getString("users.name"))
                    .lastName(resultSet.getString("users.surname"))
                    .posts(postList)
                    .region(region)
                    .role(Role.valueOf(resultSet.getString("users.role")))
                    .build();

            System.out.println(resultSet.getLong("users.id") + " " + resultSet.getString("users.name") + " " +
                    resultSet.getString("users.surname") + " " + resultSet.getString("users.role") + " "
                    + resultSet.getLong("users.region_id") + " " + resultSet.getLong("posts.id"));
        }
        return user;
    }

    @Override
    public User save(User user) throws SQLException{
        preparedStatement = ConnectionUtils.getPreparedStatement(SqlQueries.SAVE_USER.getSQL());
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getLastName());
        preparedStatement.setString(3, user.getRole().name());
        preparedStatement.setLong(4, user.getRegion().getId());
        preparedStatement.executeUpdate();
        preparedStatement = ConnectionUtils.connection.prepareStatement(SqlQueries.GET_USER_BY_ID.getSQL());
        preparedStatement.setLong(1, user.getId());
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.last();
        List<Post> postList = new ArrayList<>();
        postList.add(Post.builder().id(resultSet.getLong("posts.id")).content(resultSet.getString("posts.content"))
                .created(resultSet.getTimestamp("posts.created")).updated(resultSet.getTimestamp("posts.updated")).build());
        Region region = Region.builder().id(resultSet.getLong("regions.id")).name(resultSet.getString("regions.name")).build();
        user = User.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .lastName(resultSet.getString("surname"))
                .posts(postList)
                .region(region)
                .role(Role.valueOf(resultSet.getString("role")))
                .build();
        return user;

    }

    @Override
    public User update(User user) throws SQLException {
        preparedStatement = ConnectionUtils.getPreparedStatement(SqlQueries.UPDATE_USER.getSQL());
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getLastName());
        preparedStatement.setString(3, user.getRole().name());
        preparedStatement.setLong(4, user.getId());
        preparedStatement.executeUpdate();
        return null;
    }

    @Override
    public List<User> getAll() throws SQLException {
        List<Post> postList = new ArrayList<>();
        List<User> users = new ArrayList<>();
        User user;
        Post post;
        preparedStatement = ConnectionUtils.getPreparedStatement(SqlQueries.GET_ALL_USERS.getSQL());
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Region region = Region.builder().id(resultSet.getLong("regions.id")).name(resultSet.getString("regions.name")).build();
            if (users.size() > 0 && users.get(users.size() - 1).getId() != resultSet.getLong("users.id")) {
                postList = new ArrayList<>();
            }
            post = Post.builder().id(resultSet.getLong("posts.id")).content(resultSet.getString("posts.content"))
                    .created(resultSet.getTimestamp("posts.created")).updated(resultSet.getTimestamp("posts.updated")).user_id(resultSet.getLong("posts.user_id")).build();
            postList.add(post);
            user = User.builder()
                    .id(resultSet.getLong("users.id"))
                    .name(resultSet.getString("users.name"))
                    .lastName(resultSet.getString("users.surname"))
                    .posts(postList)
                    .region(region)
                    .role(Role.valueOf(resultSet.getString("users.role")))
                    .build();
            if (users.size() <= 0) {
                users.add(user);
            }
            if (users.size() > 0 && users.get(users.size() - 1).getId() != resultSet.getLong("users.id")) {
                users.add(user);
            }

            System.out.println(resultSet.getLong("users.id") + " " + resultSet.getString("users.name") + " " +
                    resultSet.getString("users.surname") + " " + resultSet.getString("users.role") + " "
                    + resultSet.getLong("users.region_id") + " " + resultSet.getLong("posts.id"));
        }
        return users;
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        ConnectionUtils.getStatement().executeUpdate(SqlQueries.DELETE_USER_BY_ID.getSQL() + id);
    }
}
