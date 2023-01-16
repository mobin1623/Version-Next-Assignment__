package com.example.version_next_assignment.Database;

import com.example.version_next_assignment.Model.WeatherModel;
import com.example.version_next_assignment.Utils.Constants;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface WeatherDao {

    @Insert
    long insertWeatherData(WeatherModel weatherModel);

    @Query("DELETE FROM "+ Constants.TABLE_NAME)
    int deleteData();


    @Query("SELECT COUNT(*) FROM " +  Constants.TABLE_NAME)
    int getCount();


    @Query("SELECT * FROM " + Constants.TABLE_NAME)
    List<WeatherModel> getLocalData();
}
