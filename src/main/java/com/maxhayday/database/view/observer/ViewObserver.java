package com.maxhayday.database.view.observer;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public interface ViewObserver {
    void create() throws ParseException, IOException, SQLException, ClassNotFoundException;

    void update(Long id) throws IOException;

    void getById(Long id);

    void getAll() throws IOException, ParseException;

    void delete(Long id) throws ParseException, IOException;
}
