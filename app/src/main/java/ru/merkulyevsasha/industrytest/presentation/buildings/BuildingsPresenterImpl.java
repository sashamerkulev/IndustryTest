package ru.merkulyevsasha.industrytest.presentation.buildings;

import java.util.List;

import ru.merkulyevsasha.industrytest.domain.BuildingsInteractor;
import ru.merkulyevsasha.industrytest.pojo.Building;

/**
 * Created by sasha_merkulev on 04.04.2017.
 */
public class BuildingsPresenterImpl {

    private final BuildingsInteractor inter;
    private BuildingsActivity view;

    public BuildingsPresenterImpl(BuildingsInteractor inter) {
        this.inter = inter;
    }

    void onStart(BuildingsActivity view) {
        this.view = view;
        onStart();
    }

    private void onStart(){

        if (view == null)
            return;

        view.showProgress();
        inter.loadFromDb(new BuildingsInteractor.BuildingsCallback() {
            @Override
            public void success(List<Building> items) {
                if (view == null)
                    return;

                view.hideProgress();

                if (items.size() == 0){
                    onRefresh();
                } else {
                    view.show(items);
                }
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

    void onRefresh(){
        if (view == null)
            return;

        view.showProgress();
        inter.loadFromHttp(new BuildingsInteractor.BuildingsCallback() {
            @Override
            public void success(List<Building> items) {
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



}