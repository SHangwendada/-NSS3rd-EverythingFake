package com.swdd.ezezez;

import android.app.Application;
import android.content.Context;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes4.dex */
public class Stub extends Application {
    public native void initializeJNI();

    public native void start();

    static {
        System.loadLibrary("ezezez");
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        initializeJNI();
        copyAssetsToInternalStorage(this);
        start();
    }

    private void copyAssetsToInternalStorage(Context context) {
        String outputDir = context.getFilesDir().getPath();
        try {
            String[] assets = context.getAssets().list("");
            if (assets != null) {
                for (String asset : assets) {
                    copyAsset(context, asset, outputDir);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void copyAsset(Context context, String assetName, String outputDir) {
        File outFile = new File(outputDir, assetName);
        if (outFile.exists()) {
            return;
        }
        InputStream in = null;
        OutputStream out = null;
        try {
            try {
                try {
                    in = context.getAssets().open(assetName);
                    out = new FileOutputStream(outFile);
                    byte[] buffer = new byte[1024];
                    while (true) {
                        int read = in.read(buffer);
                        if (read == -1) {
                            break;
                        }
                        out.write(buffer, 0, read);
                    }
                    out.flush();
                    if (in != null) {
                        in.close();
                    }
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    if (in != null) {
                        in.close();
                    }
                    if (out != null) {
                        out.close();
                    }
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    in.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                    throw th;
                }
            }
            if (0 != 0) {
                out.close();
            }
            throw th;
        }
    }
}