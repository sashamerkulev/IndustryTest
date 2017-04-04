package ru.merkulyevsasha.industrytest.repositories.http;

import java.util.List;

import ru.merkulyevsasha.industrytest.pojo.Building;

/**
 * Created by sasha_merkulev on 04.04.2017.
 */

public interface HttpDataSource {

    List<Building> getBuildings();

}
