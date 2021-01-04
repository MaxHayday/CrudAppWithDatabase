package com.maxhayday.database.view.observer;

import com.maxhayday.database.controller.RegionController;
import com.maxhayday.database.model.Region;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class RegionViewObserver implements ViewObserver {
    private RegionController regionController;
    private String data;
    private List<Region> regionsList;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private Region region;
    private String regionStr = "";

    public RegionViewObserver() {
        regionController = new RegionController();
    }

    @Override
    public void create() throws IOException {
        System.out.println("Write a region: ");
        regionStr = reader.readLine();
        regionController.save(null, regionStr);
    }

    @Override
    public void update(Long id) throws IOException {
        System.out.println("Write new region: ");
        data = reader.readLine();
        regionController.update(id, data);
    }

    @Override
    public void getById(Long id) {
        regionsList = regionController.getAll();
        if (regionsList.isEmpty()) {
            System.out.println("You haven`t region with " + id + " id.");
            return;
        }
    }

    @Override
    public void getAll() {
        regionsList = regionController.getAll();
        if (regionsList.isEmpty()) {
            System.out.println("You haven`t regions.");
            return;
        }
        System.out.println("===========");
        System.out.printf("%-5s%-20s%n", "ID", "REGION");
        System.out.println("===========");
        for (Region i :
                regionsList) {
            System.out.printf("%-5s%-25s%n", i.getId(), i.getName());
        }
    }

    @Override
    public void delete(Long id) {
        region = regionController.getById(id);
        if (region == null) {
            System.out.println("You haven`t region with " + id + " id.");
        }
        regionController.deleteById(id);
    }

    public void deleteRegionById(Long id) {
        region = regionController.getById(id);
        if (region == null) {
            System.out.println("You haven`t region with " + id + " id.");
        }
        regionController.deleteRegionById(id);
    }
}
