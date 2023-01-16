package com.example.version_next_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.karumi.dexter.BuildConfig;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        checkPermission();
    }

    private void checkPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Dexter.withContext(getApplicationContext())
                    .withPermissions(
                            Manifest.permission.ACCESS_FINE_LOCATION)
                    .withListener(new MultiplePermissionsListener() {
                        @Override
                        public void onPermissionsChecked(MultiplePermissionsReport report) {
                            // check if all permissions are granted
                            if (report.areAllPermissionsGranted()) {
                                // do you work now
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();
                            }else{
                                List<PermissionDeniedResponse> responses = report.getDeniedPermissionResponses();
                                StringBuilder permissionsDenied = new StringBuilder();
                                for (PermissionDeniedResponse response : responses) {
//                                                        permissionsDenied.append(response.getPermissionName()).append(" ") ;
                                    if (response.getPermissionName().contains("WRITE_EXTERNAL_STORAGE")){
                                        permissionsDenied.append("Storage").append("\n");
                                    }else if (response.getPermissionName().contains("CAMERA")){
                                        permissionsDenied.append("Camera").append("\n");
                                    }else if (response.getPermissionName().contains("READ_PHONE_STATE")){
                                        permissionsDenied.append("Phone").append("\n");
                                    }else if (response.getPermissionName().contains("ACCESS_FINE_LOCATION")){
                                        permissionsDenied.append("Location").append("\n");
                                    }
                                }
                                Log.d("TAG","Permission denied : " + permissionsDenied.toString());

                            }

                            // check for permanent denial of any permission
                            if (report.isAnyPermissionPermanentlyDenied()) {
                                // permission is denied permenantly, navigate user to app settings
                                Toast.makeText(getApplicationContext(),
                                        "Some permission denied, please allow all permission ",
                                        Toast.LENGTH_LONG).show();
                                settingScreenDialog();
                            }
                            else if (!report.areAllPermissionsGranted()){
                                Toast.makeText(getApplicationContext(), "Some permission denied, " +
                                                "please allow all permission ",
                                        Toast.LENGTH_LONG).show();
                                settingScreenDialog();

                            }
                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                            token.continuePermissionRequest();
                        }
                    })
                    .onSameThread()
                    .check();
        }else{
            Dexter.withContext(getApplicationContext())
                    .withPermissions(

                            Manifest.permission.ACCESS_FINE_LOCATION)
                    .withListener(new MultiplePermissionsListener() {
                        @Override
                        public void onPermissionsChecked(MultiplePermissionsReport report) {
                            // check if all permissions are granted
                            if (report.areAllPermissionsGranted()) {
                                // do you work now
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                finish();
                            }else{
                                List<PermissionDeniedResponse> responses = report.getDeniedPermissionResponses();
                                StringBuilder permissionsDenied = new StringBuilder();
                                for (PermissionDeniedResponse response : responses) {
//                                                        permissionsDenied.append(response.getPermissionName()).append(" ") ;
                                    if (response.getPermissionName().contains("WRITE_EXTERNAL_STORAGE")){
                                        permissionsDenied.append("Storage").append("\n");
                                    }else if (response.getPermissionName().contains("CAMERA")){
                                        permissionsDenied.append("Camera").append("\n");
                                    }else if (response.getPermissionName().contains("READ_PHONE_STATE")){
                                        permissionsDenied.append("Phone").append("\n");
                                    }else if (response.getPermissionName().contains("ACCESS_FINE_LOCATION")){
                                        permissionsDenied.append("Location").append("\n");
                                    }

                                }
                                Log.d("TAG","Permission denied : " + permissionsDenied.toString());

                            }

                            // check for permanent denial of any permission
                            if (report.isAnyPermissionPermanentlyDenied()) {
                                // permission is denied permenantly, navigate user to app settings
                                Toast.makeText(getApplicationContext(), "Some permission denied, " +
                                                "please allow all permission ",
                                        Toast.LENGTH_LONG).show();
                                settingScreenDialog();
                            }
                            else if (!report.areAllPermissionsGranted()){
                                Toast.makeText(getApplicationContext(), "Some permission denied, " +
                                                "please allow all permission ",
                                        Toast.LENGTH_LONG).show();
                                settingScreenDialog();

                            }

                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions,
                                                                       PermissionToken token) {
                            token.continuePermissionRequest();
                        }
                    })
                    .onSameThread()
                    .check();
        }

    }

    private void settingScreenDialog() {

        AlertDialog alertDialog_setting;
        AlertDialog.Builder builder_setting = new AlertDialog.Builder(Splash.this);
        builder_setting.setTitle("Allow Permission").setCancelable(false)
                .setMessage("Permission denied :"  + "\n" + " Please allow all the permissions for further app use.")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                Uri.parse("package:" + BuildConfig.APPLICATION_ID)));
                        finish();


                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

        alertDialog_setting = builder_setting.create();
        alertDialog_setting.show();
    }
}