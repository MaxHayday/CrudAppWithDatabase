package com.maxhayday.database.repository.jdbc;

import com.maxhayday.database.ConnectionUtils;
import com.maxhayday.database.SqlQueries;
import com.maxhayday.database.model.Region;
import com.maxhayday.database.repository.RegionRepository;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.maxhayday.database.ConnectionUtils.preparedStatement;


public class JdbcRegionRepositoryImpl implements RegionRepository {
    private Region region;

    public JdbcRegionRepositoryImpl() throws SQLException, IOException, ClassNotFoundException {
        ConnectionUtils connectionUtils = new ConnectionUtils();
    }

    @Override
    public Region getById(Long id) throws SQLException {
        ResultSet resultSet = ConnectionUtils.getStatement().executeQuery(SqlQueries.GET_REGION_BY_ID.getSQL() + id);
        while (resultSet.next()) {
            region = Region.builder().id(resultSet.getLong("id"))
                    .name(resultSet.getString("name")).build();
        }
        return region;
    }


    @Override
    public Region save(Region region) throws SQLException{
        preparedStatement = ConnectionUtils.getPreparedStatement(SqlQueries.SAVE_REGION.getSQL());
        preparedStatement.setString(1, region.getName());
        preparedStatement.executeUpdate();
        ResultSet resultSet = ConnectionUtils.getStatement().executeQuery(SqlQueries.GET_ALL_REGIONS.getSQL());
        resultSet.last();
        region = Region.builder().id(resultSet.getLong("id")).name(resultSet.getString("name")).build();
        return region;
    }

    @Override
    public Region update(Region region) throws SQLException {
        preparedStatement = ConnectionUtils.getPreparedStatement(SqlQueries.UPDATE_REGION.getSQL() + region.getId());
        preparedStatement.setString(1, region.getName());
        preparedStatement.executeUpdate();
        return getById(region.getId());
    }

    @Override
    public List<Region> getAll() throws SQLException {
        List<Region> regionList = new ArrayList<>();
        ResultSet resultSet = ConnectionUtils.getStatement().executeQuery(SqlQueries.GET_ALL_REGIONS.getSQL());
        while (resultSet.next()) {
            Region region = Region.builder().id(resultSet.getLong("id"))
                    .name(resultSet.getString("name"))
                    .build();
            regionList.add(region);
        }
        return regionList;
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        ConnectionUtils.getStatement().executeUpdate(SqlQueries.DELETE_REGION_BY_ID.getSQL() + id);
    }
}
