package ru.merkulyevsasha.industrytest.di;



import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.merkulyevsasha.industrytest.domain.BuildingsInteractor;
import ru.merkulyevsasha.industrytest.presentation.buildings.BuildingsPresenterImpl;


@Module
public class PresentersModule {

    @Singleton
    @Provides
    BuildingsPresenterImpl providesNewsPresenter(BuildingsInteractor inter) {
        return new BuildingsPresenterImpl(inter);
    }


}
