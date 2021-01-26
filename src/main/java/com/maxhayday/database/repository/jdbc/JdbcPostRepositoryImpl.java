package com.maxhayday.database.repository.jdbc;

import com.maxhayday.database.ConnectionUtils;
import com.maxhayday.database.SqlQueries;
import com.maxhayday.database.model.Post;
import com.maxhayday.database.model.Region;
import com.maxhayday.database.repository.PostRepository;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.maxhayday.database.ConnectionUtils.preparedStatement;


public class JdbcPostRepositoryImpl implements PostRepository {

    public JdbcPostRepositoryImpl() throws SQLException, IOException, ClassNotFoundException {
        ConnectionUtils connectionUtils = new ConnectionUtils();
    }

    @Override
    public Post getById(Long id) throws SQLException {
        Post post = null;
        ResultSet resultSet = ConnectionUtils.getStatement().executeQuery(SqlQueries.GET_POST_BY_ID.getSQL() + id);
        while (resultSet.next()) {
            post = Post.builder().id(resultSet.getLong("id"))
                    .content(resultSet.getString("content"))
                    .created(resultSet.getTimestamp("created"))
                    .updated(resultSet.getTimestamp("updated"))
                    .user_id(resultSet.getLong("user_id"))
                    .build();
        }
        return post;
    }

    public List<Post> getPostListOfUserId(Long id) throws SQLException {
        List<Post> postList = new ArrayList<>();
        Post post;
        ResultSet resultSet = ConnectionUtils.getStatement().executeQuery(SqlQueries.GET_POST_BY_USER_ID.getSQL() + id);
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
    }

    @Override
    public Post save(Post post) throws SQLException {
        preparedStatement = ConnectionUtils.getPreparedStatement(SqlQueries.SAVE_POST.getSQL());
        preparedStatement.setString(1, post.getContent());
        preparedStatement.setTimestamp(2, post.getCreated());
        preparedStatement.setTimestamp(3, post.getUpdated() == null ? null : post.getUpdated());
        preparedStatement.setLong(4, post.getUser_id() == null ? 0 : post.getUser_id());
        preparedStatement.executeUpdate();
        ResultSet resultSet = ConnectionUtils.getStatement().executeQuery(SqlQueries.GET_ALL_POSTS.getSQL());
        resultSet.last();
        post = Post.builder().id(resultSet.getLong("id"))
                .content(resultSet.getString("content"))
                .created(resultSet.getTimestamp("created"))
                .updated(resultSet.getTimestamp("updated"))
                .user_id(resultSet.getLong("user_id"))
                .build();
        return post;
    }

    @Override
    public Post update(Post post) throws SQLException {
        preparedStatement = ConnectionUtils.getPreparedStatement(SqlQueries.UPDATE_POST.getSQL() + post.getId());
        preparedStatement.setString(1, post.getContent());
        preparedStatement.setTimestamp(2, post.getUpdated());
        preparedStatement.executeUpdate();
        return getById(post.getId());
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        ConnectionUtils.getStatement().executeUpdate(SqlQueries.DELETE_POST_BY_ID.getSQL() + id);
    }

    @Override
    public List<Post> getAll() throws SQLException {
        List<Post> postList = new ArrayList<>();
        ResultSet resultSet = ConnectionUtils.getStatement().executeQuery(SqlQueries.GET_ALL_POSTS.getSQL());
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
    }

    @Override
    public void deleteByUserId(Long id) throws SQLException {
        ConnectionUtils.getStatement().executeUpdate(SqlQueries.DELETE_POST_BY_USER_ID.getSQL() + id);
    }
}
