package com.example.version_next_assignment.Database;

import android.content.Context;

import com.example.version_next_assignment.Model.WeatherModel;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = WeatherModel.class,version = 1)
public abstract class WeatherDatabase extends RoomDatabase {

    public abstract WeatherDao weatherDao();


    private static WeatherDatabase database;

    public static WeatherDatabase getInstance(Context context){
        if (null == database){
            database = buildDatabaseInstance(context);
        }
        return database;

    }

    private static WeatherDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,WeatherDatabase.class, "WeatherDB")
                .allowMainThreadQueries().build();
    }

    public void cleanUp(){
        if(database != null){
            database.close();
            database = null;
        }
    }

}
