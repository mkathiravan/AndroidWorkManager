package net.kathir.workmanager;

import android.content.Context;
import android.util.Log;

import androidx.work.BackoffPolicy;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.concurrent.TimeUnit;

public class WorkManagerJob {

    private static final String TAG = WorkManagerJob.class.getSimpleName();

    private WorkManager mWorkManager;

    public static WorkManagerJob instance;


    public WorkManagerJob()
    {
        if (instance != null) {
            throw new RuntimeException("Cannot instantiate single object using constructor. Use its #getInstance() method");
        }
    }


    public static WorkManagerJob getInstance()
    {
        if(instance == null)
        {
            synchronized (WorkManagerJob.class)
            {
                if(instance == null)
                {
                    instance = new WorkManagerJob();
                }
            }
        }
        return instance;
    }

    public void oneTimeSchedule(Context context)
    {
        Log.d(TAG,"One_Time_Schedule_Method_Called ");

        mWorkManager = WorkManager.getInstance(context.getApplicationContext());
        OneTimeWorkRequest postLocationRequest = new OneTimeWorkRequest.Builder(ScheduleServiceTask.class)
                .addTag(TAG).build();
        mWorkManager.enqueue(postLocationRequest);

    }

    public void periodicSchedule(Context context)
    {
        Log.d(TAG,"OnRepeat_Tracking_Called ");
        mWorkManager = WorkManager.getInstance(context.getApplicationContext());

        PeriodicWorkRequest repeatRequest = new PeriodicWorkRequest.Builder(ScheduleServiceTask.class, 15, TimeUnit.MINUTES)
                        .addTag(TAG)
                        .setBackoffCriteria(BackoffPolicy.LINEAR, PeriodicWorkRequest.MIN_BACKOFF_MILLIS, TimeUnit.MILLISECONDS)
                        .build();

        mWorkManager.enqueueUniquePeriodicWork("Regular Polling Checker", ExistingPeriodicWorkPolicy.KEEP, repeatRequest);


    }


}
