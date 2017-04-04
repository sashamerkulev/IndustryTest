package ru.merkulyevsasha.industrytest.di;



import javax.inject.Singleton;

import dagger.Component;
import ru.merkulyevsasha.industrytest.JobServiceUpdater;
import ru.merkulyevsasha.industrytest.presentation.buildings.BuildingsActivity;
import ru.merkulyevsasha.industrytest.presentation.flats.FlatsActivity;


@Singleton
@Component(modules={AppModule.class, DbModule.class, RepositoriesModule.class, InteractorsModule.class, PresentersModule.class})
public interface DbComponent {

    void inject(BuildingsActivity context);
    void inject(FlatsActivity context);

    void inject(JobServiceUpdater context);

}
