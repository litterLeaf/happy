package com.yinshan.happycash.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.MimeTypeMap;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by huxin on 2018/4/16.
 */

public class FileUtil {

    private final static String TAG = "FileUtil";

    private static String HAPPY_DIR = Environment.getExternalStorageDirectory().getPath() + File.separator;
    private static String LOG_DIR = HAPPY_DIR + File.separator + "happy_cash";

    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    public static File getCustomLogDir() {
        File dir = new File(LOG_DIR);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                return null;
            }
        }
        return dir;
    }

    public static File getLogDir(Context context) {
        try {
            if (isExternalStorageWritable()) {
                return context.getExternalFilesDir("Log");
            } else {
                File file = context.getFilesDir();
                File dir = new File(file, "Log");
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                return dir;
            }
        } catch (Exception e) {
            return null;
        }
    }


    public static void writeLog(Context context, String message) {
        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//        File dir = getLogDir(context);
        File dir = getCustomLogDir();
        if (dir == null) {
            Log.e(TAG, "Directory not created");
            return;
        }

        File file = new File(dir, today + "Log.txt");

        try {
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(message);
            bufferedWriter.newLine();
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File getCacheFile(Activity activity){
        File storagePath;

        if (TextUtils.equals(Environment.MEDIA_MOUNTED, Environment.getExternalStorageState())) {
            storagePath = new File(Environment.getExternalStorageDirectory() + Util.cacheDir);
            if (!storagePath.exists()) {
                storagePath.mkdirs();
            }
        } else {
            storagePath = activity.getCacheDir();
        }
        return storagePath;
    }

    /**
     * @return The MIME type for the given file.
     */
    public static String getMimeType(File file) {

        String extension = getExtension(file.getName());

        if (extension.length() > 0)
            return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension.substring(1));

        return "application/octet-stream";
    }

    /**
     * Gets the extension of a file name, like ".png" or ".jpg".
     *
     * @param uri
     * @return Extension including the dot("."); "" if there is no extension;
     *         null if uri was null.
     */
    public static String getExtension(String uri) {
        if (uri == null) {
            return null;
        }

        int dot = uri.lastIndexOf(".");
        if (dot >= 0) {
            return uri.substring(dot);
        } else {
            // No extension.
            return "";
        }
    }

}
