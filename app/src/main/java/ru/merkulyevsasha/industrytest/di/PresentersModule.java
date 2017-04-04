package ru.merkulyevsasha.industrytest.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.merkulyevsasha.industrytest.domain.BuildingsInteractor;
import ru.merkulyevsasha.industrytest.domain.FlatsInteractor;
import ru.merkulyevsasha.industrytest.presentation.buildings.BuildingsPresenterImpl;
import ru.merkulyevsasha.industrytest.presentation.flats.FlatsPresenterImpl;


@Module
public class PresentersModule {

    @Singleton
    @Provides
    BuildingsPresenterImpl providesBuildingsPresenter(BuildingsInteractor inter) {
        return new BuildingsPresenterImpl(inter);
    }

    @Singleton
    @Provides
    FlatsPresenterImpl providesFlatsPresenter(FlatsInteractor inter) {
        return new FlatsPresenterImpl(inter);
    }

}
