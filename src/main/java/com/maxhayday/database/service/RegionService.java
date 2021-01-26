package com.maxhayday.database.service;

import com.maxhayday.database.model.Region;
import com.maxhayday.database.repository.RegionRepository;
import com.maxhayday.database.repository.jdbc.JdbcRegionRepositoryImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class RegionService {
    private final RegionRepository regionRepository;
    private Region region;

    public RegionService() throws SQLException, IOException, ClassNotFoundException {
        this.regionRepository = new JdbcRegionRepositoryImpl();
    }


    public Region getById(Long id) throws IOException, ParseException, SQLException, ClassNotFoundException {
        region = regionRepository.getById(id);
        return region;
    }

    public Region save(Region region) throws IOException, SQLException, ClassNotFoundException {
        region = regionRepository.save(region);
        return region;
    }

    public Region update(Region region) throws IOException, SQLException, ClassNotFoundException {
        region = regionRepository.update(region);
        return region;
    }

    public List<Region> getAll() throws IOException, ParseException, SQLException, ClassNotFoundException {
        List<Region> regionList = regionRepository.getAll();
        return regionList;
    }

    public void deleteById(Long id) throws IOException, SQLException, ClassNotFoundException {
        regionRepository.deleteById(id);
    }
}
