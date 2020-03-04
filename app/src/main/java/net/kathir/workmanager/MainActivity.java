package net.kathir.workmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button oneTimeRequest,periodicRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        oneTimeRequest = (Button)findViewById(R.id.one_time_request);
        periodicRequest = (Button)findViewById(R.id.periodic_request);


        oneTimeRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                WorkManagerJob.getInstance().oneTimeSchedule(getApplicationContext());
            }
        });

        periodicRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                WorkManagerJob.getInstance().periodicSchedule(getApplicationContext());
            }
        });
    }
}
