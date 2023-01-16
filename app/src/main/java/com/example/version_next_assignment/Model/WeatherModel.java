package com.example.version_next_assignment.Model;

import com.example.version_next_assignment.Utils.Constants;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = Constants.TABLE_NAME)
public class WeatherModel {
   @PrimaryKey(autoGenerate = true)
   private long w_Id;
   String temp;
   String feelstemp;
   String minTemp;
   String maxTemp;
   String icon;
   String desc;
   String name;
   String humidity;
   String pressure;

   public long getW_Id() {
      return w_Id;
   }

   public void setW_Id(long w_Id) {
      this.w_Id = w_Id;
   }

   public String getTemp() {
      return temp;
   }

   public void setTemp(String temp) {
      this.temp = temp;
   }

   public String getFeelstemp() {
      return feelstemp;
   }

   public void setFeelstemp(String feelstemp) {
      this.feelstemp = feelstemp;
   }

   public String getMinTemp() {
      return minTemp;
   }

   public void setMinTemp(String minTemp) {
      this.minTemp = minTemp;
   }

   public String getMaxTemp() {
      return maxTemp;
   }

   public void setMaxTemp(String maxTemp) {
      this.maxTemp = maxTemp;
   }

   public String getIcon() {
      return icon;
   }

   public void setIcon(String icon) {
      this.icon = icon;
   }

   public String getDesc() {
      return desc;
   }

   public void setDesc(String desc) {
      this.desc = desc;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }


   public String getHumidity() {
      return humidity;
   }

   public void setHumidity(String humidity) {
      this.humidity = humidity;
   }

   public String getPressure() {
      return pressure;
   }

   public void setPressure(String pressure) {
      this.pressure = pressure;
   }
}
