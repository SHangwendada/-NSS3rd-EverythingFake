package com.swdd.ezezez;

import android.app.Activity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/* loaded from: classes2.dex */
public class PermissionActivity extends Activity {
    private static final int REQUEST_CODE_PERMISSIONS = 1;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
        } else {
            proceedWithAppLogic();
        }
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 1) {
            if (iArr.length > 0 && iArr[0] == 0) {
                proceedWithAppLogic();
            } else {
                finish();
            }
        }
    }

    private void proceedWithAppLogic() {
        Stub stub = (Stub) getApplication();
        stub.copyAssetsToInternalStorage(this);
        stub.start();
        finish();
    }
}