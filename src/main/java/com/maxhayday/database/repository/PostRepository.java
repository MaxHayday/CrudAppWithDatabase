package com.maxhayday.database.repository;

import com.maxhayday.database.model.Post;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface PostRepository extends GenericRepository<Post, Long> {
    Post getById(Long id) throws IOException, ParseException, ClassNotFoundException, SQLException;

    Post save(Post post) throws IOException, SQLException, ClassNotFoundException;

    List<Post> getAll() throws IOException, ParseException, ClassNotFoundException, SQLException;

    List<Post> getPostListOfUserId(Long id) throws ClassNotFoundException, SQLException;

    Post update(Post post) throws ClassNotFoundException, SQLException;

    void deleteById(Long id) throws IOException, ClassNotFoundException, SQLException;

    void deleteByUserId(Long id) throws ClassNotFoundException, SQLException;
}
