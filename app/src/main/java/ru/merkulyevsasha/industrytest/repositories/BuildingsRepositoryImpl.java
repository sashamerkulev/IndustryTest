package ru.merkulyevsasha.industrytest.repositories;

import java.util.List;

import ru.merkulyevsasha.industrytest.pojo.Building;
import ru.merkulyevsasha.industrytest.repositories.db.DbDataSource;
import ru.merkulyevsasha.industrytest.repositories.http.HttpDataSource;

/**
 * Created by sasha_merkulev on 04.04.2017.
 */

public class BuildingsRepositoryImpl implements BuildingsRepository {

    private DbDataSource db;
    private HttpDataSource http;

    public BuildingsRepositoryImpl(DbDataSource db, HttpDataSource http) {
        this.db = db;
        this.http = http;
    }

    @Override
    public List<Building> getDbBuildings() {
        return db.getBuildings();
    }

    @Override
    public List<Building> getHttpBuildings() {
        return http.getBuildings();
    }

    @Override
    public void saveBuildings(List<Building> items) {
        db.saveBuildings(items);
    }
}