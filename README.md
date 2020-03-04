# AndroidWorkManager

# WorkManager?

WorkManager is a task scheduler, it makes easy to specify the asynchronous task. The Work Manager API helps create the task and hand it to the Work Manager to run immediately or at an appropriate time as mentioned.

Types of work supported by WorkManager
WorkManager is support two type work. OneTimeWorkRequest and PeriodicWorkRequest.

1. OneTimeWorkRequest
As per name OneTimeWorkRequest for non-repeating work. Mean if you want to enqueue requests for one time only then we used this WorkRequest.

2. PeriodicWorkRequest
This WorkRequest used for repeating or recurring works. In Simple words, PeriodicWorkRequest works as cron job in android. In other words, you want to perform some repetitive task in Android than we use PeriodicWorkRequest. This type of work is repeating multiple times until it is cancelled. The first execution happening immediately or given Constraints. The next execution will happen as per the given interval. The minimum interval should be 15 min. It can be delayed due to OS battery optimizations and Not meeting all Constraints, such as doze mode, no network connectivity etc.

  
 # The following operations has to do while doing the workmanger

 a) Get WorkManager instance
 b) Create PeriodicWorkRequest
 c) Get WorkInfo by Id
 d) Enqueue work request
 e) Cancel an enqueued work
 
 # Get WorkManager instance
 WorkManager mWorkManager = WorkManager.getInstance();

 # Create PeriodicWorkRequest
 ![image](https://user-images.githubusercontent.com/39657409/75847748-0c748900-5e06-11ea-9b45-d71603e58d45.png)
 
 # For now, I’m creating request that be run every 15 min.
 
   PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(MyWorker.class, 15, TimeUnit.MINUTES).build();
   
 # Get WorkInfo by Id
  mWorkManager.getWorkInfoByIdLiveData(workRequest.getId()).observe(this, new Observer<WorkInfo>() {
            @Override
            public void onChanged(@Nullable WorkInfo workInfo) {
                if (workInfo != null) {
                    WorkInfo.State state = workInfo.getState();
                    tvWorkStatus.append(state.toString() + "\n");
                }
            }
        });
 
 # Enqueue work request
 btnSchedulePeriodicWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWorkManager.enqueue(workRequest);
            }
    });


![image](https://user-images.githubusercontent.com/39657409/75847215-915ea300-5e04-11ea-9d42-89242026adb7.png)
