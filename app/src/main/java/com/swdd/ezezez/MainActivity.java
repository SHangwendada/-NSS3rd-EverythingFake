package com.swdd.ezezez;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.swdd.ezezez.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    private static final int PERMISSION_REQUEST_CODE = 1;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        checkAndRequestPermissions();


    }



    private boolean checkAndRequestPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PERMISSION_REQUEST_CODE);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
              //  start();
            } else {
                Toast.makeText(this, "Permissions denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * A native method that is implemented by the 'ezezez' native library,
     * which is packaged with this application.
     */
    // public native String stringFromJNI();
     //public native void start();
    public static String getAppDirPath(Context context) {
        ApplicationInfo applicationInfo = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.DONUT) {
            applicationInfo = context.getApplicationInfo();
        }
        String appDir = applicationInfo.dataDir;
        return appDir;
    }
    public void onClick(View V){
        TextView input = findViewById(R.id.editText);
        String inputString = input.getText().toString();
        if(Check(inputString)){
            Toast.makeText(MainActivity.this,"GOOD!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this,"NONONO!", Toast.LENGTH_SHORT).show();
        }
    }

    public native Boolean Check(String input);
}
