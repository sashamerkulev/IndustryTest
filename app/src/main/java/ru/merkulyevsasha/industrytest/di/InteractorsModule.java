package ru.merkulyevsasha.industrytest.di;


import java.util.concurrent.ExecutorService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.merkulyevsasha.industrytest.domain.BuildingsInteractor;
import ru.merkulyevsasha.industrytest.domain.BuildingsInteractorImpl;
import ru.merkulyevsasha.industrytest.domain.FlatsInteractor;
import ru.merkulyevsasha.industrytest.domain.FlatsInteractorImpl;
import ru.merkulyevsasha.industrytest.repositories.BuildingsRepository;
import ru.merkulyevsasha.industrytest.repositories.FlatsRepository;


@Module
public class InteractorsModule {

    @Singleton
    @Provides
    BuildingsInteractor providesBuildingsInteractor(ExecutorService serv, BuildingsRepository repo) {
        return new BuildingsInteractorImpl(serv, repo);
    }

    @Singleton
    @Provides
    FlatsInteractor providesFlatsInteractor(ExecutorService serv, FlatsRepository repo) {
        return new FlatsInteractorImpl(serv, repo);
    }


}
