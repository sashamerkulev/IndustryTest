package ru.merkulyevsasha.industrytest.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.merkulyevsasha.industrytest.repositories.BuildingsRepository;
import ru.merkulyevsasha.industrytest.repositories.BuildingsRepositoryImpl;
import ru.merkulyevsasha.industrytest.repositories.db.DbDataSource;
import ru.merkulyevsasha.industrytest.repositories.http.HttpDataSource;


@Module
public class RepositoriesModule {

    @Singleton
    @Provides
    BuildingsRepository providesNewsRepository(DbDataSource db, HttpDataSource http) {
        return new BuildingsRepositoryImpl(db, http);
    }

}
