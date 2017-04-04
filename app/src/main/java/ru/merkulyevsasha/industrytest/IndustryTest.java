package ru.merkulyevsasha.industrytest;

import android.app.Application;

import ru.merkulyevsasha.industrytest.di.AppModule;
import ru.merkulyevsasha.industrytest.di.DaggerDbComponent;
import ru.merkulyevsasha.industrytest.di.DbComponent;
import ru.merkulyevsasha.industrytest.di.DbModule;
import ru.merkulyevsasha.industrytest.di.InteractorsModule;
import ru.merkulyevsasha.industrytest.di.PresentersModule;
import ru.merkulyevsasha.industrytest.di.RepositoriesModule;

/**
 * Created by sasha_merkulev on 04.04.2017.
 */

public class IndustryTest extends Application {

    private static DbComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerDbComponent.builder()
                .appModule(new AppModule(this))
                .dbModule(new DbModule())
                .repositoriesModule(new RepositoriesModule())
                .interactorsModule(new InteractorsModule())
                .presentersModule(new PresentersModule())
                .build();
    }

    public static DbComponent getComponent() {
        return component;
    }
}
