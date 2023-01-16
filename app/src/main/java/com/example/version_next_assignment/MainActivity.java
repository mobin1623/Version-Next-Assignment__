package com.example.version_next_assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.version_next_assignment.Database.WeatherDatabase;
import com.example.version_next_assignment.Utils.AppUtils;
import com.example.version_next_assignment.Utils.GPSTracker;
import com.example.version_next_assignment.Model.WeatherModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private WeatherViewModel weatherViewModel;

    private TextView tvTemp, tvMinMax ,tvDesc,tvCity,tvHumidity,tvPressure;
    private GPSTracker gpsTracker;
    private WeatherDatabase db;
    List<WeatherModel> list = new ArrayList<>();
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTemp = findViewById(R.id.tvTemp);
        tvMinMax = findViewById(R.id.tvMinMax);
        imageView = findViewById(R.id.imageView);
        tvDesc = findViewById(R.id.tvDesc);
        tvCity = findViewById(R.id.tvCity);
        tvHumidity = findViewById(R.id.tvHumidity);
        tvPressure = findViewById(R.id.tvPressure);

        db = WeatherDatabase.getInstance(this);

        gpsTracker = new GPSTracker(getApplicationContext());


        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);
        weatherViewModel.init();
        weatherViewModel.getWeatherViewModelLiveData().observe(this, new Observer<WeatherModel>() {

            @Override
            public void onChanged(WeatherModel weatherModel) {
                if (weatherModel != null){
                    tvTemp.setText(weatherModel.getTemp() + "(Feel's Like : " +weatherModel.getFeelstemp() + ")");
                    tvMinMax.setText(weatherModel.getMaxTemp() + "/" + weatherModel.getMinTemp());
                    tvDesc.setText(weatherModel.getDesc());
                    Glide.with(getApplicationContext()).load("http://openweathermap.org/img/w/" +
                            weatherModel.getIcon() + ".png").into(imageView);
                    tvCity.setText(weatherModel.getName());
                    tvHumidity.setText( "Humidity : " + weatherModel.getHumidity() + "%");
                    tvPressure.setText("Pressure : " + weatherModel.getPressure() + "hPa");


                }
            }
        });


        getWeatherData();
    }


    private void getWeatherData(){
        if (AppUtils.isInternetAvailable(getApplicationContext())){
            if (gpsTracker.canGetLocation()){
                weatherViewModel.getWeatherData(db,String.valueOf(gpsTracker.getLatitude()),String.valueOf(gpsTracker.getLongitude()));
            }
        }else {

            if (db.weatherDao().getCount() > 0){
                list = db.weatherDao().getLocalData();
                for (int i = 0; i < list.size(); i++) {
                    tvTemp.setText(list.get(i).getTemp() + "(Feel's Like : " +list.get(i).getFeelstemp() + ")");
                    tvMinMax.setText(list.get(i).getMaxTemp() + "/" + list.get(i).getMinTemp());
                    tvDesc.setText(list.get(i).getDesc());
                    Glide.with(getApplicationContext()).load("http://openweathermap.org/img/w/" +
                            list.get(i).getIcon() + ".png").into(imageView);

                    tvCity.setText(list.get(i).getName());
                    tvHumidity.setText( "Humidity : " + list.get(i).getHumidity() + "%");
                    tvPressure.setText("Pressure : " + list.get(i).getPressure() + "hPa");
                }


            }else {
                tvTemp.setText("No data");
                tvMinMax.setText("No data");
                Toast.makeText(getApplicationContext(), "Local Data not available.", Toast.LENGTH_SHORT).show();
            }


        }
    }

    private void getLocalData(){

    }
}