package com.example.jaska.buspassengertraffic;

import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


import android.app.NotificationManager;
import android.support.v4.app.NotificationCompat;

import android.content.Context;

public class MainActivity extends AppCompatActivity {
    private TextView busNum;
    private TextView showData;
    private TextView showData69;
    NotificationCompat.Builder mBuilder;
    PendingIntent mResultPendingIntent;
    TaskStackBuilder mTaskStackBuilder;
    Intent mResultIntent;
    NotificationManager mNotificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        busNum = findViewById(R.id.busNum);
        showData = findViewById(R.id.showData);
        showData69 = findViewById(R.id.showData69);
        mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);

        mBuilder.setContentTitle("Notification Alert");
        mBuilder.setContentTitle("Requested Bus is Almost full");
        mResultIntent = new Intent(this, MainActivity.class);
        mTaskStackBuilder = TaskStackBuilder.create(this);
        mTaskStackBuilder.addParentStack(MainActivity.this);
        mTaskStackBuilder.addNextIntent(mResultIntent);
        mResultPendingIntent =  mTaskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(mResultPendingIntent);
        mNotificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);



    }

    public void getData(View view) {
        //mNotificationManager.notify(1,mBuilder.build());
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        Api api = retrofit.create(Api.class);
        Call<List<Hero>> call = api.getBus();
        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {
                ProgressBar prg;
                prg = (ProgressBar) findViewById(R.id.progressBar);
                prg.setMinimumHeight(80);
                prg.setMinimumWidth(1000);
                List<Hero> busses = response.body();
                if (busNum.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Enter a valid Bus number", Toast.LENGTH_SHORT).show();
                    return;
                }
                int num = Integer.parseInt(busNum.getText().toString());

                if (num == 104) {
                    Hero bus = busses.get(0);
                    showData69.setText("104 Towards  Wonderland");

                    if (bus.getCapacityzone1().equals("green")) {
                        showData.setText("Light Traffic");
                        showData.setTextColor(Color.GREEN);
                        prg.setProgress(25);
                        prg.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);
                    } else if (bus.getCapacityzone1().equals("yellow")) {
                        showData.setText("Medium Traffic");
                        showData.setTextColor(Color.YELLOW);
                        prg.setProgress(50);
                        prg.getProgressDrawable().setColorFilter(Color.YELLOW, android.graphics.PorterDuff.Mode.SRC_IN);
                    } else if (bus.getCapacityzone1().equals("red")) {
                        showData.setText("Heavy Traffic");
                        showData.setTextColor(Color.RED);
                        prg.setProgress(80);
                        prg.getProgressDrawable().setColorFilter(Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);

                        mNotificationManager.notify(1,mBuilder.build());


                    } else {
                        showData.setText("ERROR");
                        showData.setTextColor(Color.RED);

                    }
                } else if (num == 111) {
                    showData69.setText("111 Towards ME 4444");
                    Hero bus = busses.get(1);
                    if (bus.getCapacityzone2().equals("green")) {
                        showData.setText("Light Traffic");
                        showData.setTextColor(Color.GREEN);
                        prg.setProgress(25);
                        prg.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);
                    } else if (bus.getCapacityzone2().equals("yellow")) {
                        showData.setText("Medium Traffic");
                        showData.setTextColor(Color.YELLOW);
                        prg.setProgress(50);
                        prg.getProgressDrawable().setColorFilter(Color.YELLOW, android.graphics.PorterDuff.Mode.SRC_IN);
                    } else if (bus.getCapacityzone2().equals("red")) {
                        showData.setText("Heavy Traffic");
                        showData.setTextColor(Color.RED);
                        prg.setProgress(80);
                        prg.getProgressDrawable().setColorFilter(Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
                        mNotificationManager.notify(1,mBuilder.build());

                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Enter a valid Bus number", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }
}

