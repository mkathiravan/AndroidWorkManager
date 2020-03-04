package net.kathir.workmanager;

import android.content.Context;
import android.media.MediaPlayer;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class ScheduleServiceTask extends Worker {

    private static final String TAG = ScheduleServiceTask.class.getSimpleName();
    Context context;

    public ScheduleServiceTask(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;
    }

    @NonNull
    @Override
    public Result doWork() {
        call_play_method();
        return Result.success();
    }

    private void call_play_method() {
        MediaPlayer mp = MediaPlayer.create(context, R.raw.moideen);
        mp.start();

    }
}
