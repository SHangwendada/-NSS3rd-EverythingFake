package com.swdd.ezezez;

import android.app.Application;
import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
public class Stub extends Application {

    static {
        System.loadLibrary(a("ezezez03"));
    }
    public native void start();

    @Override
    public void onCreate() {
       // Log.d("TAG", a("ezezez03"));
        super.onCreate();
        Intent intent = new Intent(this, PermissionActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void copyAssetsToInternalStorage(Context context) {
        String outputDir = context.getFilesDir().getPath(); // /data/data/com.swdd.ezezez/files
        try {
            // 获取assets目录下的所有文件
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

    /**
     * 复制单个asset文件到内部存储
     *
     * @param context   上下文
     * @param assetName assets目录下的文件名
     * @param outputDir 输出的目标目录路径
     */
    public void copyAsset(Context context, String assetName, String outputDir) {
        File outFile = new File(outputDir, assetName);
        if (outFile.exists()) {
            return;
        }

        InputStream in = null;
        OutputStream out = null;
        try {
            in = context.getAssets().open(assetName);
            out = new FileOutputStream(outFile);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static String a(String input) {
        if (input == null || input.length() == 0) {
            return input; // 如果输入为空或长度为0，直接返回原始输入
        }

        // 获取最后一个字符
        char lastChar = input.charAt(input.length() - 2);

        // 将最后一个字符的 ASCII 值加 3
        char modifiedChar = (char) (lastChar + 8);

        // 将原始字符串的前部分与修改后的字符拼接
        return input.substring(0, input.length() - 2) + modifiedChar+'3';
    }


}
