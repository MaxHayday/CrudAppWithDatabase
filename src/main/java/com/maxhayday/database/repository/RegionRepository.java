package com.maxhayday.database.repository;

import com.maxhayday.database.model.Region;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface RegionRepository extends GenericRepository<Region,Long> {
    Region getById(Long id) throws IOException, ParseException, ClassNotFoundException, SQLException;

    Region save(Region region) throws IOException, SQLException, ClassNotFoundException;

    Region update(Region region) throws IOException, ClassNotFoundException, SQLException;

    List<Region> getAll() throws IOException, ParseException, ClassNotFoundException, SQLException;

    void deleteById(Long id) throws IOException, ClassNotFoundException, SQLException;
}
