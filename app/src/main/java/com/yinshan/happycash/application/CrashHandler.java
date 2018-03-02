package com.yinshan.happycash.application;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import com.yinshan.happycash.utils.LoggerWrapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by huxin on 2018/3/2.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler{

    //系统默认的UncaughtException处理类
    private        Thread.UncaughtExceptionHandler mDefaultHandler;
    //CrashHandler实例
    private static CrashHandler                    INSTANCE;
    //程序的Context对象
    private Context mContext;
    //用来存储设备信息和异常信息
    private Map<String, String> infos = new HashMap<String, String>();

    /**
     * 获取CrashHandler实例 ,单例模式
     */
    public static synchronized CrashHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CrashHandler();
        }
        return INSTANCE;

    }

    /**
     * 初始化
     *
     * @param context
     */
    public void init(Context context) {
        mContext = context;
        //获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        //设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        boolean result = false;
        try {
            result = handleException(ex);
        } catch (Exception e) {

        }
        if (!result && mDefaultHandler != null) {
            //如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            try {
                //Need to update
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                LoggerWrapper.e(e.toString());
            }
            //退出程序
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }

    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false.
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        //收集设备参数信息
        collectDeviceInfo(mContext);
        //保存日志文件
        saveCrashInfo2File(ex);
        return true;
    }

    /**
     * 收集设备参数信息
     *
     * @param ctx
     */
    public void collectDeviceInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            LoggerWrapper.e("CrashHandler.NameNotFoundException---> error occured when collect package info", e);
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
            } catch (Exception e) {
                LoggerWrapper.e("CrashHandler.NameNotFoundException---> an error occured when collect crash info", e);
            }
        }
    }

    /**
     * 保存错误信息到文件中
     *
     * @param ex
     * @return 返回文件名称, 便于将文件传送到服务器
     */
    private String saveCrashInfo2File(Throwable ex) {

        StringBuffer sb = new StringBuffer();
        sb.append("-------------------------start------------------------------");
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");
        }

        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);
        sb.append("-------------------------end---------------------------------");
        LoggerWrapper.e(sb.toString());
        Log.v("CrashHandler","into");
        String str = formCrashMsg(sb.toString()).toString();
        Log.v("CrashHandler","into str is "+str);
//        LoanModel mLoanModel = RetrofitUtils.createTokenService(LoanModel.class);
//        mLoanModel.uploadCrash(getSID(),str)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new DefaultSubscriber<Object>() {
//                    @Override
//                    public void onSuccess(Object o) {
//
//                    }
//
//                    @Override
//                    public void onFailure(int errorCode, String errorMessage) {
//
//                    }
//                });
        return null;
    }

    private JSONObject formCrashMsg(String crashMsg) {
        JSONObject CrashMsgEntity = new JSONObject();
        Long totalNumber = 1L;
        Long latestTime = 0L;
        Long earliestTime = 0L;

        JSONArray crashMsgArray = new JSONArray();

        JSONObject crashMsgObject = new JSONObject();

        try {
            crashMsgObject.put("createTime", new Date().getTime());
            crashMsgObject.put("crashType", crashMsg.substring(0, crashMsg.indexOf("\n")));
            crashMsgObject.put("crashMsg", crashMsg);
            crashMsgArray.put(crashMsgObject);

            //------------------------
            PackageInfo packageInfo = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
//            CrashMsgEntity.put("protocolName", ProtocolName.CRASH_MSG);
//            CrashMsgEntity.put("protocolVersion", ProtocolVersion.V_2_0);
            CrashMsgEntity.put("versionName", packageInfo.versionName);
            CrashMsgEntity.put("versionCode", packageInfo.versionCode);

            CrashMsgEntity.put("totalNumber", totalNumber);
            CrashMsgEntity.put("latestTime", latestTime);
            CrashMsgEntity.put("earliestTime", earliestTime);
            CrashMsgEntity.put("data", crashMsgArray);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return CrashMsgEntity;

    }
}
