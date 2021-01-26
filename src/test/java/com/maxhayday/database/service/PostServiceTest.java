package com.maxhayday.database.service;

import com.maxhayday.database.model.Post;
import com.maxhayday.database.repository.PostRepository;
import com.maxhayday.database.repository.jdbc.JdbcPostRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import static com.maxhayday.database.data.PostTestData.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class PostServiceTest {

    private PostRepository postRepository;
    private PostService postService;

    @BeforeEach
    void setUp() throws SQLException, IOException, ClassNotFoundException {
        postRepository = mock(JdbcPostRepositoryImpl.class);
        postService = new PostService();
    }

    @Test
    void getById() throws ClassNotFoundException, SQLException, ParseException, IOException {
        Post post = postService.getById(1l);
        assertEquals(expectedDataOfPostGetById(), post);
    }

    @Test
    void save() throws SQLException, IOException, ClassNotFoundException {
        Post post = postService.save(testDataOfPostForCreating());
        assertEquals(expectedCreatedDataOfPost(), post);
    }

    @Test
    void getAll() throws ClassNotFoundException, SQLException, ParseException, IOException {
        List<Post> postList = postService.getAll();
        assertEquals(expectedDataOfAllPosts(), postList);
    }

    @Test
    void deleteById() throws SQLException, IOException, ClassNotFoundException, ParseException {
        postService.deleteById(1l);
        assertNull(postService.getById(1l));
    }

    @Test
    void deleteByUserId() throws SQLException, ClassNotFoundException, IOException, ParseException {
        postService.deleteByUserId(2l);
        assertNull(postService.getById(2l));
    }

    @Test
    void update() throws SQLException, ClassNotFoundException {
        Post post = postService.update(testDataOfPostForUpdating());
        assertEquals(testDataOfPostForUpdating(), post);
    }
}