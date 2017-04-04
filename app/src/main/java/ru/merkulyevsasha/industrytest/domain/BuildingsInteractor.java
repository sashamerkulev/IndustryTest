package ru.merkulyevsasha.industrytest.domain;

import java.util.List;

import ru.merkulyevsasha.industrytest.pojo.Building;

/**
 * Created by sasha_merkulev on 04.04.2017.
 */
public interface BuildingsInteractor {

    interface BuildingsCallback {
        void success(List<Building> items);
        void failure(Exception e);
    }

    void loadFromDb(BuildingsCallback callback);
    void loadFromHttp(BuildingsCallback callback);

}