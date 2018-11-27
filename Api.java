package com.example.jaska.buspassengertraffic;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL  = "https://raw.githubusercontent.com/ahmad-chaudhry/SYSC3010Project.io/master/";
    @GET("sampledata")
    Call<List<Hero>> getBus();
}
