package ru.merkulyevsasha.industrytest.repositories;

import java.util.List;

import ru.merkulyevsasha.industrytest.pojo.Flat;
import ru.merkulyevsasha.industrytest.repositories.db.DbDataSource;
import ru.merkulyevsasha.industrytest.repositories.http.HttpDataSource;

/**
 * Created by sasha_merkulev on 04.04.2017.
 */

public class FlatsRepositoryImpl implements FlatsRepository {

    private DbDataSource db;
    private HttpDataSource http;

    public FlatsRepositoryImpl(DbDataSource db, HttpDataSource http) {
        this.db = db;
        this.http = http;
    }

    @Override
    public List<Flat> getFlats(int buildingId) {
        return db.getFlats(buildingId);
    }


}