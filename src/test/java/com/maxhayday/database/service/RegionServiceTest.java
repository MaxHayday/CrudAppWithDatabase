package com.maxhayday.database.service;

import com.maxhayday.database.model.Region;
import com.maxhayday.database.repository.RegionRepository;
import com.maxhayday.database.repository.jdbc.JdbcRegionRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import static com.maxhayday.database.data.RegionTestData.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class RegionServiceTest {
    private RegionRepository regionRepository;
    private RegionService regionService;
    private Region region;

    @BeforeEach
    void setUp() throws SQLException, IOException, ClassNotFoundException {
        regionRepository = mock(JdbcRegionRepositoryImpl.class);
        regionService = new RegionService();
    }

    @Test
    void save() throws SQLException, IOException, ClassNotFoundException {
        region = regionService.save(testDataOfRegionForCreating());
        assertEquals(expectedCreatedDataOfRegion(), region);
    }

    @Test
    void getById() throws ClassNotFoundException, SQLException, ParseException, IOException {
        region = regionService.getById(1L);
        assertEquals(expectedDataOfRegionGetById(), region);
    }

    @Test
    void update() throws SQLException, IOException, ClassNotFoundException {
        region = regionService.update(testDataOfRegionForUpdating());
        assertEquals(testDataOfRegionForUpdating(), region);
    }

    @Test
    void getAll() throws ClassNotFoundException, SQLException, ParseException, IOException {
        List<Region> regions = regionService.getAll();
        assertEquals(expectedDataOfAllRegions(), regions);
    }

    @Test
    void deleteById() throws ClassNotFoundException, SQLException, ParseException, IOException {
        regionService.deleteById(1L);
        assertNull(regionService.getById(1l));
    }
}