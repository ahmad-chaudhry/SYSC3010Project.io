package com.example.jaska.buspassengertraffic;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "http://172.20.10.11:80/";

    @GET("getdata.php")
    Call<List<Hero>> getBus();
}


/*
public interface Api {
    String BASE_URL  = "http://172.20.10.11:80/";
    @GET("getdata.php")
    Call<List<Hero>> getBus();
}
*/