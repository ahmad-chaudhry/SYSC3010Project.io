package com.example.jaska.buspassengertraffic;

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

public class MainActivity extends AppCompatActivity {
    private TextView busNum;
    private TextView showData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        busNum = findViewById(R.id.busNum);
        showData = findViewById(R.id.showData);
    }

    public void getData(View v) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        Api api = retrofit.create(Api.class);
        Call<List<Hero>> call = api.getBus();
        //Call<List<time>> call = api.gettime();
        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {
                ProgressBar prg;
                prg = (ProgressBar) findViewById(R.id.progressBar);
                prg.setMinimumHeight(80);
                prg.setMinimumWidth(100);
                List<Hero> busses = response.body();
                if (busNum.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Enter a valid Bus number", Toast.LENGTH_SHORT).show();
                    return;
                }
                int num = Integer.parseInt(busNum.getText().toString());

                if (num == 2) {
                    Hero bus = busses.get(0);
                    if (bus.getCapacityzone1().equals("green")) {
                        showData.setText("Empty");
                        showData.setTextColor(Color.GREEN);
                        prg.setProgress(25);
                        prg.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);
                    } else if (bus.getCapacityzone1().equals("yellow")) {
                        showData.setText("Half Full");
                        showData.setTextColor(Color.YELLOW);
                        prg.setProgress(50);
                        prg.getProgressDrawable().setColorFilter(Color.YELLOW, android.graphics.PorterDuff.Mode.SRC_IN);
                    } else if (bus.getCapacityzone1().equals("red")) {
                        showData.setText("Almost Full");
                        showData.setTextColor(Color.RED);
                        prg.setProgress(80);
                        prg.getProgressDrawable().setColorFilter(Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
                    } else  {
                        showData.setText("ERROR");
                        showData.setTextColor(Color.RED);

                    }
                } else if (num == 7) {
                    Hero bus = busses.get(1);
                    if (bus.getCapacityzone2().equals("green")) {
                        showData.setText("Empty");
                        showData.setTextColor(Color.GREEN);
                        prg.setProgress(25);
                        prg.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);
                    } else if (bus.getCapacityzone2().equals("yellow")) {
                        showData.setText("Half Full");
                        showData.setTextColor(Color.YELLOW);
                        prg.setProgress(50);
                        prg.getProgressDrawable().setColorFilter(Color.YELLOW, android.graphics.PorterDuff.Mode.SRC_IN);
                    } else if (bus.getCapacityzone2().equals("red")) {
                        showData.setText("Almost Full");
                        showData.setTextColor(Color.RED);
                        prg.setProgress(80);
                        prg.getProgressDrawable().setColorFilter(Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
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


