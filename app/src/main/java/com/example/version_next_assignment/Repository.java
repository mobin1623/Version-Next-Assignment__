package com.example.version_next_assignment;

import android.util.Log;

import com.example.version_next_assignment.Database.WeatherDatabase;
import com.example.version_next_assignment.Utils.AppUtils;
import com.example.version_next_assignment.Utils.Constants;
import com.example.version_next_assignment.Model.WeatherModel;
import com.example.version_next_assignment.Network.WeatherServiceInterface;

import org.json.JSONArray;
import org.json.JSONObject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Repository {
    private static final String URL = "https://api.openweathermap.org/";
    private WeatherServiceInterface serviceInterface;
    private MutableLiveData<WeatherModel> liveData;

    public Repository() {
        liveData = new MutableLiveData<>();
        serviceInterface = new Retrofit.Builder()
                .baseUrl(URL).addConverterFactory(ScalarsConverterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherServiceInterface.class);


    }

    public void getWeatherDetails(WeatherDatabase db, String lat, String lon){
        serviceInterface.getWeather(lat,lon,Constants.API_KEY).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                Log.d("TAG",response.body());
                if (response.body() != null){
                    WeatherModel weatherModel;
                    try{
                        JSONObject jsonObject = new JSONObject(response.body());
                        JSONObject main = jsonObject.getJSONObject("main");
                        weatherModel = new WeatherModel();
                        weatherModel.setTemp(AppUtils.convertKtoC(main.optString("temp") ) + "\u2103");
                        weatherModel.setFeelstemp(AppUtils.convertKtoC(main.optString("feels_like") ) + "\u2103");
                        weatherModel.setMinTemp(AppUtils.convertKtoC(main.optString("temp_min") ) + "\u2103");
                        weatherModel.setMaxTemp(AppUtils.convertKtoC(main.optString("temp_max") ) + "\u2103");
                        weatherModel.setName(jsonObject.optString("name"));
                        weatherModel.setHumidity(main.optString("humidity"));
                        weatherModel.setPressure(main.optString("pressure"));

                        JSONArray jsonArray = jsonObject.getJSONArray("weather");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject weather = jsonArray.getJSONObject(i);
                            weatherModel.setIcon(weather.optString("icon"));
                            weatherModel.setDesc(weather.getString("description"));
                        }

                        liveData.postValue(weatherModel);
                        db.weatherDao().deleteData();
                        db.weatherDao().insertWeatherData(weatherModel);


                    }catch (Exception e){
                        e.printStackTrace();

                    }

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("TAG",t.getMessage());
                liveData.postValue(null);

            }
        });
    }



    public LiveData<WeatherModel> getWeatherLivedata(){
        return liveData;
    }
}
