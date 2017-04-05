package ru.merkulyevsasha.industrytest.presentation.flats;

import java.util.List;

import ru.merkulyevsasha.industrytest.domain.FlatsInteractor;
import ru.merkulyevsasha.industrytest.pojo.Building;
import ru.merkulyevsasha.industrytest.pojo.Flat;

/**
 * Created by sasha_merkulev on 04.04.2017.
 */
public class FlatsPresenterImpl {

    private final FlatsInteractor inter;
    private FlatsActivity view;

    public FlatsPresenterImpl(FlatsInteractor inter) {
        this.inter = inter;
    }

    void onStart(FlatsActivity view) {
        this.view = view;
    }

    void onStart(Building building) {
        if (view == null)
            return;

        view.showProgress();
        inter.loadFlats(building.getId(), new FlatsInteractor.FlatsCallback() {
            @Override
            public void success(List<Flat> items) {
                if (view == null)
                    return;

                view.hideProgress();
                view.show(items);
            }

            @Override
            public void failure(Exception e) {
                if (view == null)
                    return;

                view.hideProgress();
                view.showErrorMessage();
            }
        });
    }

    void onStop() {
        this.view = null;
    }

    void onRemove (int flatId){
        inter.remove(flatId);
    }
}