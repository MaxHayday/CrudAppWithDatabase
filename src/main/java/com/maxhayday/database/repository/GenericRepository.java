package com.maxhayday.database.repository;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface GenericRepository<T, ID> {
    T getById(Long id) throws IOException, ParseException, ClassNotFoundException, SQLException;

    T save(T t) throws IOException, ParseException, ClassNotFoundException, SQLException;

    T update(T t) throws IOException, ParseException, ClassNotFoundException, SQLException;

    List<T> getAll() throws IOException, ParseException, ClassNotFoundException, SQLException;

    void deleteById(Long id) throws IOException, ClassNotFoundException, SQLException;
}
