package com.swdd.ezezez;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.swdd.ezezez.databinding.ActivityMainBinding;

/* loaded from: classes4.dex */
public class MainActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 1;
    private ActivityMainBinding binding;

    public native Boolean Check(String str);

    static {
        System.loadLibrary("ezezez");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding inflate = ActivityMainBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setContentView(inflate.getRoot());
    }

    private boolean checkAndRequestPermissions() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.READ_EXTERNAL_STORAGE") != 0) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
            return false;
        }
        return true;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length <= 0 || grantResults[0] != 0) {
                Toast.makeText(this, "Permissions denied", 0).show();
            }
        }
    }

    public static String getAppDirPath(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        String appDir = applicationInfo.sourceDir;
        return appDir;
    }

    public void onClick(View V) {
        TextView input = (TextView) findViewById(R.id.editText);
        String inputString = input.getText().toString();
        if (Check(inputString).booleanValue()) {
            Toast.makeText(this, "GOOD!", 0).show();
        } else {
            Toast.makeText(this, "NONONO!", 0).show();
        }
    }
}