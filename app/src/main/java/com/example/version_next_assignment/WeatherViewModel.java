package com.example.version_next_assignment;

import android.app.Application;
import android.content.Context;

import com.example.version_next_assignment.Database.WeatherDatabase;
import com.example.version_next_assignment.Model.WeatherModel;
import com.example.version_next_assignment.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class WeatherViewModel extends AndroidViewModel {

    private Repository repository;
    private LiveData<WeatherModel> weatherViewModelLiveData;

    public WeatherViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(){
        repository = new Repository();
        weatherViewModelLiveData = repository.getWeatherLivedata();
    }

    public void getWeatherData(WeatherDatabase db, String lat, String lon){
        repository.getWeatherDetails(db,lat,lon);
    }

    public LiveData<WeatherModel> getWeatherViewModelLiveData(){
        return weatherViewModelLiveData;
    }
}
