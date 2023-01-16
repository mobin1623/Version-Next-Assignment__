package com.example.version_next_assignment.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.text.NumberFormat;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class AppUtils {

   public static boolean isInternetAvailable(Context context) {
      ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
      NetworkInfo activeNetworkInfo = null;
      if (connectivityManager != null) {
         activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
      }
      return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
   }

   public static String convertKtoC(String temp){
      float kelvin = Float.parseFloat(temp);
      float celsius = kelvin - 273.15F;

      NumberFormat nf = NumberFormat.getNumberInstance();
      nf.setMaximumFractionDigits(0);

      return String.format("%.0f", celsius);
   }
}
