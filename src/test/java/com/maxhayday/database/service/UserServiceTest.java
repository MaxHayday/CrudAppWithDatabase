package com.maxhayday.database.service;

import com.maxhayday.database.model.User;
import com.maxhayday.database.repository.UserRepository;
import com.maxhayday.database.repository.jdbc.JdbcUserRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import static com.maxhayday.database.data.UserTestData.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;


    @BeforeEach
    void setUp() throws SQLException, IOException, ClassNotFoundException, ParseException {
        userRepository = mock(JdbcUserRepositoryImpl.class);
        userService = new UserService();
    }

    @Test
    void save() throws ClassNotFoundException, SQLException, ParseException, IOException {
        User createdUser = userService.save(testDataOfUserForCreating());
        assertEquals(expectedCreatedDataOfUser(), createdUser);
    }

    @Test
    void getById() throws ClassNotFoundException, SQLException, ParseException, IOException {
        User user = userService.getById(1L);
        assertEquals(expectedDataOfUserGetById(), user);
    }

    @Test
    void update() throws ClassNotFoundException, SQLException, ParseException, IOException {
        User updatedUser = userService.update(testDataOfUserForUpdating());
        assertEquals(testDataOfUserForUpdating(), updatedUser);
    }

    @Test
    void getAll() throws ClassNotFoundException, SQLException, ParseException, IOException {
        List<User> users = userService.getAll();
        assertEquals(expectedDataOfAllUsers(), users);
    }

    @Test
    void deleteById() throws SQLException, IOException, ClassNotFoundException, ParseException {
        userService.deleteById(1l);
        assertNull(userService.getById(1L));
    }
}