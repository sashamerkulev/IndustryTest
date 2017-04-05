package ru.merkulyevsasha.industrytest.di;


import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.merkulyevsasha.industrytest.repositories.db.DbDataSource;
import ru.merkulyevsasha.industrytest.repositories.db.DbDataSourceImpl;
import ru.merkulyevsasha.industrytest.repositories.http.HttpDataSource;
import ru.merkulyevsasha.industrytest.repositories.http.HttpDataSourceImpl;


@Module
public class DbModule {

    @Singleton
    @Provides
    DbDataSource providesDbDataSource(Context context) {
        return new DbDataSourceImpl(context);
    }

    @Singleton
    @Provides
    HttpDataSource providesHttpDataSource(Context context) {
        return new HttpDataSourceImpl(context);
    }

}
