package ru.merkulyevsasha.industrytest.domain;

import java.util.List;
import java.util.concurrent.ExecutorService;

import ru.merkulyevsasha.industrytest.pojo.Building;
import ru.merkulyevsasha.industrytest.repositories.BuildingsRepository;


/**
 * Created by sasha_merkulev on 04.04.2017.
 */

public class BuildingsInteractorImpl implements BuildingsInteractor {

    private BuildingsRepository repo;
    private ExecutorService executor;

    public BuildingsInteractorImpl(ExecutorService executor, BuildingsRepository repo) {
        this.executor = executor;
        this.repo = repo;
    }

    @Override
    public void loadFromDb(final BuildingsCallback callback) {
        executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    List<Building> items = repo.getDbBuildings();
                    callback.success(items);
                } catch(Exception e){
                    callback.failure(new BuildingsException(e));
                }
            }
        });
    }

    @Override
    public void loadFromHttp(final BuildingsCallback callback) {
        executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    List<Building> items = repo.getHttpBuildings();
                    repo.saveBuildings(items);
                    callback.success(items);
                } catch(Exception e){
                    callback.failure(new BuildingsException(e));
                }
            }
        });
    }
}