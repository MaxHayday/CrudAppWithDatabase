package com.maxhayday.database.controller;

import com.maxhayday.database.model.Region;
import com.maxhayday.database.service.RegionService;
import com.maxhayday.database.service.UserService;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class RegionController {
    private RegionService regionService;
    private UserService userService;
    private Region region;

    public RegionController() {
        regionService = new RegionService();
        userService = new UserService();
    }

    public void save(Long id, String name) {
        region = Region.builder().id(id).name(name).build();
        try {
            regionService.save(region);
        } catch (IOException | SQLException | ClassNotFoundException e) {
            System.out.println("Wrong id of name.");
        }
    }

    public List<Region> getAll() {
        try {
            return regionService.getAll();
        } catch (IOException | ParseException | SQLException | ClassNotFoundException e) {
            System.out.println("You haven`t regions.");
        }
        return null;
    }

    public void update(Long id, String name) {
        region = Region.builder().id(id).name(name).build();
        try {
            regionService.update(region);
        } catch (IOException | SQLException | ClassNotFoundException e) {
            System.out.println("Can`t update region.");
        }
    }

    public void deleteById(Long id) {
        try {
            regionService.deleteById(id);
        } catch (IOException | SQLException | ClassNotFoundException e) {
            System.out.println("Can`t delete region.");
        }
    }

    public Region getById(Long id) {
        try {
            return regionService.getById(id);
        } catch (IOException | ParseException | SQLException | ClassNotFoundException e) {
            System.out.println("Wrong id.");
        }
        return null;
    }

    public void deleteRegionById(Long id) {
        try {
            regionService.deleteById(id);
        } catch (IOException | SQLException | ClassNotFoundException e) {
            System.out.println("Can`t delete region.");
        }
    }
}
