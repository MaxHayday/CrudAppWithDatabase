package com.maxhayday.database.data;

import com.maxhayday.database.model.Region;

import java.util.ArrayList;
import java.util.List;

public class RegionTestData {
    public static Region testDataOfRegionForCreating() {
        Region region = Region.builder().id(3L).name("POL").build();
        return region;
    }

    public static Region testDataOfRegionForUpdating() {
        Region region = Region.builder().id(2L).name("IRLL").build();
        return region;
    }

    public static Region expectedCreatedDataOfRegion() {
        Region region = Region.builder().id(3L).name("POL").build();
        return region;
    }

    public static Region expectedDataOfRegionGetById() {
        Region region = Region.builder().id(1L).name("UKR").build();
        return region;
    }

    public static List<Region> expectedDataOfAllRegions() {
        ArrayList<Region> regions = new ArrayList<>();
        Region region = Region.builder().id(1l).name("UKR").build();
        regions.add(region);
        region = Region.builder().id(2l).name("IRL").build();
        regions.add(region);
        return regions;
    }
}
