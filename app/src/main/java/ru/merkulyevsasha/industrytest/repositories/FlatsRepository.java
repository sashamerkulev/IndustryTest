package ru.merkulyevsasha.industrytest.repositories;

import java.util.List;

import ru.merkulyevsasha.industrytest.pojo.Flat;

/**
 * Created by sasha_merkulev on 04.04.2017.
 */

public interface FlatsRepository {

    List<Flat> getFlats(int buildingId);

}
