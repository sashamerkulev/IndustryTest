package ru.merkulyevsasha.industrytest.repositories.db;


import java.util.List;

import ru.merkulyevsasha.industrytest.pojo.Building;
import ru.merkulyevsasha.industrytest.pojo.Flat;

/**
 * Created by sasha_merkulev on 04.04.2017.
 */

public interface DbDataSource {

    public List<Building> getBuildings();
    public List<Flat> getFlats(int buildingId);

    public void saveBuildings(List<Building> items);

}
