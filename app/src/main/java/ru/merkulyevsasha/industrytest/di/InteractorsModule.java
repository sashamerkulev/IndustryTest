package ru.merkulyevsasha.industrytest.di;


import java.util.concurrent.ExecutorService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.merkulyevsasha.industrytest.domain.BuildingsInteractor;
import ru.merkulyevsasha.industrytest.domain.BuildingsInteractorImpl;
import ru.merkulyevsasha.industrytest.repositories.BuildingsRepository;


@Module
public class InteractorsModule {

    @Singleton
    @Provides
    BuildingsInteractor providesBuildingsInteractor(ExecutorService serv, BuildingsRepository repo) {
        return new BuildingsInteractorImpl(serv, repo);
    }



}
