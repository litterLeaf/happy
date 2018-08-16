package com.yinshan.happycash.view.liveness.view.impl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appsflyer.AppsFlyerLib;
import com.yinshan.happycash.R;
import com.yinshan.happycash.utils.SPUtils;
import com.yinshan.happycash.utils.StringUtils;
import com.yinshan.happycash.view.main.view.impl.MainActivity;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by huxin on 2018/8/13.
 */
public class OliveResultActivity extends Activity implements View.OnClickListener{
    public static final String TAG = OliveResultActivity.class.getSimpleName();

    private TextView mOliveappResultTextView;
    private Button btnRetry;
    private Button btnBack;
    private Button toMyLoan;
    private ImageView imageResult;
    private LinearLayout mOliveResultll;
    private LinearLayout toShare;
    private TextView       content;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResources().getIdentifier("oliveapp_activity_result", "layout", getPackageName()));
        mOliveappResultTextView =  findViewById(getResources().getIdentifier("oliveappResultTextView", "id", getPackageName()));
        mOliveResultll = findViewById(R.id.ll_result_success);
        imageResult = findViewById(R.id.image_result);
        btnRetry = findViewById(R.id.btn_retry);
        btnBack =  findViewById(R.id.btn_back);
        toMyLoan =  findViewById(R.id.olive_confirm);
        toShare = findViewById(R.id.olive_to_share);
        content = findViewById(R.id.olive_content);
        toShare.setOnClickListener(this);
        toMyLoan.setOnClickListener(this);
        btnRetry.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        content.setText(StringUtils.getInstance().setStringWords(R.string.apply_success,"belasan juta berbulan dan tidak berbatas >>"));
        Boolean isSuccess = getIntent().getBooleanExtra("is_success", false);
        if (isSuccess) {
            MainActivity.chooseIndex = 1;
//            mOliveappResultTextView.setVisibility(View.VISIBLE);
            mOliveappResultTextView.setText(getText(getResources().getIdentifier("oliveapp_liveness_detection_pass_hint", "string", getPackageName())));
            btnRetry.setVisibility(View.GONE);
            btnBack.setVisibility(View.GONE);
            imageResult.setVisibility(View.GONE);
            mOliveResultll.setVisibility(View.VISIBLE);
            toShare.setVisibility(View.VISIBLE);
            imageResult.setImageDrawable(getResources().getDrawable(R.drawable.olive_yes));
            //上报 申请 事件
            String imei = SPUtils.getInstance().getImie();
            Map<String, Object> eventValue = new HashMap<>();
            eventValue.put("Apply","CustomerIMei:"+ imei);
            AppsFlyerLib.getInstance().trackEvent(getApplicationContext(),"Apply",eventValue);

            //FaceBook事件埋点  Apply
//            AppEventsLogger logger=AppEventsLogger.newLogger(OliveResultActivity.this);
//            Bundle parameters = new Bundle();
//            parameters.putString(AppEventsConstants.EVENT_NAME_INITIATED_CHECKOUT, "CustomerIMei:"+ BandaAppSP.getInstance().getImei());
//            logger.logEvent(AppEventsConstants.EVENT_NAME_INITIATED_CHECKOUT,parameters);

        } else {
            mOliveappResultTextView.setVisibility(View.VISIBLE);
            mOliveappResultTextView.setText(getText(getResources().getIdentifier("oliveapp_liveness_detection_fail_hint", "string", getPackageName())));
            btnRetry.setVisibility(View.VISIBLE);
            imageResult.setVisibility(View.VISIBLE);
            toShare.setVisibility(View.GONE);
            imageResult.setImageDrawable(getResources().getDrawable(R.drawable.olive_no));
            btnBack.setVisibility(View.GONE);
            mOliveResultll.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.olive_confirm:
//                intent = new Intent(OliveResultActivity.this, MyLoan.class);
//                intent.putExtra("OliveFlag",100);
//                startActivity(intent);
                finish();
                break;
            case R.id.olive_to_share:
//                intent=new Intent(OliveResultActivity.this, SharedActivity.class);
//                intent.putExtra("OliveFlag",100);
//                startActivity(intent);
                break;
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_retry:
                Intent intent = new Intent(OliveResultActivity.this, OliveStartActivity.class);
                OliveResultActivity.this.startActivity(intent);
                finish();
                break;
        }
    }
}
