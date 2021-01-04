package com.maxhayday.database.repository;

import com.maxhayday.database.model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface UserRepository extends GenericRepository<User, Long> {
    User getById(Long id) throws IOException, ParseException, ClassNotFoundException, SQLException;

    User save(User user) throws IOException, ParseException, ClassNotFoundException, SQLException;

    User update(User user) throws IOException, ParseException, ClassNotFoundException, SQLException;

    List<User> getAll() throws IOException, ParseException, ClassNotFoundException, SQLException;

    void deleteById(Long id) throws IOException, ClassNotFoundException, SQLException;

}
