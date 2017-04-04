package ru.merkulyevsasha.industrytest.domain;

import java.util.List;

import ru.merkulyevsasha.industrytest.pojo.Flat;

/**
 * Created by sasha_merkulev on 04.04.2017.
 */
public interface FlatsInteractor {

    interface FlatsCallback {
        void success(List<Flat> items);
        void failure(Exception e);
    }

    void loadFlats(final int buildingId, final FlatsCallback callback);

}