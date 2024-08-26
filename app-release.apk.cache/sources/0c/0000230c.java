package com.swdd.ezezez;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: classes2.dex */
public class Stub extends Application {
    public native void start();

    static {
        System.loadLibrary(a("ezezez80"));
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        Intent intent = new Intent(this, PermissionActivity.class);
        intent.addFlags(268435456);
        startActivity(intent);
    }

    public void copyAssetsToInternalStorage(Context context) {
        String path = context.getFilesDir().getPath();
        try {
            String[] list = context.getAssets().list("");
            if (list != null) {
                for (String str : list) {
                    copyAsset(context, str, path);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r4v10, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r4v8, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r5v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v3, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r5v6 */
    public void copyAsset(Context context, String str, String str2) {
        Throwable th;
        FileOutputStream fileOutputStream;
        IOException e;
        File file = new File(str2, (String) str);
        if (file.exists()) {
            return;
        }
        try {
            try {
                try {
                    context = context.getAssets().open(str);
                    try {
                        fileOutputStream = new FileOutputStream(file);
                    } catch (IOException e2) {
                        e = e2;
                        fileOutputStream = null;
                    } catch (Throwable th2) {
                        th = th2;
                        str = 0;
                        if (context != 0) {
                            try {
                                context.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                                throw th;
                            }
                        }
                        if (str != 0) {
                            str.close();
                        }
                        throw th;
                    }
                } catch (IOException e4) {
                    fileOutputStream = null;
                    e = e4;
                    context = 0;
                } catch (Throwable th3) {
                    str = 0;
                    th = th3;
                    context = 0;
                }
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = context.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                    fileOutputStream.flush();
                    if (context != 0) {
                        context.close();
                    }
                    fileOutputStream.close();
                } catch (IOException e5) {
                    e = e5;
                    e.printStackTrace();
                    if (context != 0) {
                        context.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                }
            } catch (Throwable th4) {
                th = th4;
            }
        } catch (IOException e6) {
            e6.printStackTrace();
        }
    }

    static String a(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        return str.substring(0, str.length() - 1) + ((char) (str.charAt(str.length() - 1) + 3));
    }
}