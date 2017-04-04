package ru.merkulyevsasha.industrytest.repositories;


import java.util.List;

import ru.merkulyevsasha.industrytest.pojo.Building;

/**
 * Created by sasha_merkulev on 04.04.2017.
 */

public interface BuildingsRepository {

    List<Building> getDbBuildings();
    List<Building> getHttpBuildings();

    void saveBuildings(List<Building> items);
}
