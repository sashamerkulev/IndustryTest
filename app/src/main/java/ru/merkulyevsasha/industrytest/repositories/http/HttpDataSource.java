package ru.merkulyevsasha.industrytest.repositories.http;

import java.util.List;

import ru.merkulyevsasha.industrytest.pojo.Building;
import ru.merkulyevsasha.industrytest.pojo.Flat;

/**
 * Created by sasha_merkulev on 04.04.2017.
 */

public interface HttpDataSource {

    public List<Building> getBuildings();

}
