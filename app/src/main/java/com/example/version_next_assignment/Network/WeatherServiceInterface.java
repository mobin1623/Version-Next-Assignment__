package com.example.version_next_assignment.Network;

import com.example.version_next_assignment.Model.WeatherModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherServiceInterface {

   @GET("data/2.5/weather")
   Call<String> getWeather(
           @Query("lat") String lat,
           @Query("lon") String lon,
           @Query("appid") String apiKey);
}
