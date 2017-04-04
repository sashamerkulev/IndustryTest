package ru.merkulyevsasha.industrytest;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.util.Log;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ru.merkulyevsasha.industrytest.pojo.Building;
import ru.merkulyevsasha.industrytest.repositories.BuildingsRepository;
import ru.merkulyevsasha.industrytest.repositories.BuildingsRepositoryImpl;
import ru.merkulyevsasha.industrytest.repositories.db.DbDataSourceImpl;
import ru.merkulyevsasha.industrytest.repositories.http.HttpDataSourceImpl;

/**
 * Created by sasha_merkulev on 04.04.2017.
 */

public class JobServiceUpdater extends JobService{

    private static final String TAG = JobServiceUpdater.class.getSimpleName();

    private static final int MINUTES = 5;
    private static final int JOB_ID = 999;

    ExecutorService executor;
    BuildingsRepository repo;

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "onStartJob");

        final ExecutorService executor = Executors.newSingleThreadExecutor();
        final BuildingsRepository repo = new BuildingsRepositoryImpl(new DbDataSourceImpl(getApplicationContext()), new HttpDataSourceImpl());

        executor.submit(new Runnable() {
            @Override
            public void run() {
                try{
                    Log.d(TAG, "getting data");
                    List<Building> items = repo.getHttpBuildings();
                    Log.d(TAG, "saving data");
                    repo.saveBuildings(items);
                    Log.d(TAG, "succefully done");
                } catch(Exception e){
                    Log.d(TAG, "error getting data");
                }
            }
        });

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "onStopJob");
        return true;
    }

    public static void registerJobService(Context context){
        Log.d(TAG, "registering job");
        ComponentName serviceName = new ComponentName(context, JobServiceUpdater.class);
        JobInfo jobInfo = new JobInfo.Builder(JOB_ID, serviceName)
                //.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                //.setRequiresDeviceIdle(true)
                .setPeriodic(MINUTES*60*1000)
                //.setRequiresCharging(true)
                .build();
        try {
            JobScheduler scheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
            int result = scheduler.schedule(jobInfo);
            if (result == JobScheduler.RESULT_SUCCESS) {
                Log.d(TAG, "succefull register job service");
            }
        } catch (Exception e){
            Log.d(TAG, "exception shile registration job service");
        }
    }


}
