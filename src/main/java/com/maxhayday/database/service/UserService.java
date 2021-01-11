package com.maxhayday.database.service;

import com.maxhayday.database.model.User;
import com.maxhayday.database.repository.UserRepository;
import com.maxhayday.database.repository.jdbc.JdbcUserRepositoryImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class UserService {
    private final UserRepository userRepository;
    private User user;

    public UserService() throws SQLException, IOException, ClassNotFoundException {
        this.userRepository = new JdbcUserRepositoryImpl();
    }

    public User getById(Long id) throws IOException, ParseException, SQLException, ClassNotFoundException {
        user = userRepository.getById(id);
        return user;
    }

    public User save(User user) throws IOException, ParseException, SQLException, ClassNotFoundException {
        user = userRepository.save(user);
        return user;
    }

    public User update(User user) throws IOException, ParseException, SQLException, ClassNotFoundException {
        user = userRepository.update(user);
        return user;
    }

    public List<User> getAll() throws IOException, ParseException, SQLException, ClassNotFoundException {
        List<User> list = userRepository.getAll();
        return list;
    }

    public void deleteById(Long id) throws IOException, SQLException, ClassNotFoundException {
        userRepository.deleteById(id);
    }

}
