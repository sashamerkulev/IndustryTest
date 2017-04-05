package ru.merkulyevsasha.industrytest.domain;

import java.util.List;
import java.util.concurrent.ExecutorService;

import ru.merkulyevsasha.industrytest.pojo.Flat;
import ru.merkulyevsasha.industrytest.repositories.FlatsRepository;


/**
 * Created by sasha_merkulev on 04.04.2017.
 */

public class FlatsInteractorImpl implements FlatsInteractor {

    private final FlatsRepository repo;
    private final ExecutorService executor;

    public FlatsInteractorImpl(ExecutorService executor, FlatsRepository repo) {
        this.executor = executor;
        this.repo = repo;
    }

    @Override
    public void loadFlats(final int buildingId, final FlatsCallback callback) {
        executor.submit(new Runnable() {
            @Override
            public void run() {
                try{
                    List<Flat> items = repo.getFlats(buildingId);
                    callback.success(items);
                } catch(Exception e){
                    callback.failure(new FlatsException(e));
                }
            }
        });
    }

    @Override
    public void remove(final int flatId) {
        executor.submit(new Runnable() {
            @Override
            public void run() {
                try{
                    repo.delete(flatId);
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}