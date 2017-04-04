package ru.merkulyevsasha.industrytest.repositories.db;


import java.util.List;

import ru.merkulyevsasha.industrytest.pojo.Building;
import ru.merkulyevsasha.industrytest.pojo.Flat;

/**
 * Created by sasha_merkulev on 04.04.2017.
 */

public interface DbDataSource {

    List<Building> getBuildings();
    List<Flat> getFlats(int buildingId);

    void saveBuildings(List<Building> items);

}
