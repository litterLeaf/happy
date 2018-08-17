package com.yinshan.happycash.view.liveness.view.impl

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

import com.appsflyer.AppsFlyerLib
import com.yinshan.happycash.R
import com.yinshan.happycash.utils.SPUtils
import com.yinshan.happycash.utils.StringUtils
import com.yinshan.happycash.view.main.MainActivity


import java.util.HashMap

/**
 * 活体检测结果页
 * 有af上报和Facebook打点上报
 */
class OliveResultActivity : Activity(), View.OnClickListener {

    private var mOliveappResultTextView: TextView? = null
    private var btnRetry: Button? = null
    private var btnBack: Button? = null
    private var toMyLoan: Button? = null
    private var imageResult: ImageView? = null
    private var mOliveResultll: LinearLayout? = null
    private var toShare: LinearLayout? = null
    private var content: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(resources.getIdentifier("oliveapp_activity_result", "layout", packageName))
        mOliveappResultTextView = findViewById(resources.getIdentifier("oliveappResultTextView", "id", packageName))
        mOliveResultll = findViewById(R.id.ll_result_success)
        imageResult = findViewById(R.id.image_result)
        btnRetry = findViewById(R.id.btn_retry)
        btnBack = findViewById(R.id.btn_back)
        toMyLoan = findViewById(R.id.olive_confirm)
        toShare = findViewById(R.id.olive_to_share)
        content = findViewById(R.id.olive_content)
        toShare!!.setOnClickListener(this)
        toMyLoan!!.setOnClickListener(this)
        btnRetry!!.setOnClickListener(this)
        btnBack!!.setOnClickListener(this)
        content!!.text = StringUtils.getInstance().setStringWords(R.string.apply_success, "belasan juta berbulan dan tidak berbatas >>")
        val isSuccess = intent.getBooleanExtra("is_success", false)
        if (isSuccess) {
            MainActivity.chooseIndex = 1
            //            mOliveappResultTextView.setVisibility(View.VISIBLE);
            mOliveappResultTextView!!.text = getText(resources.getIdentifier("oliveapp_liveness_detection_pass_hint", "string", packageName))
            btnRetry!!.visibility = View.GONE
            btnBack!!.visibility = View.GONE
            imageResult!!.visibility = View.GONE
            mOliveResultll!!.visibility = View.VISIBLE
            toShare!!.visibility = View.VISIBLE
            imageResult!!.setImageDrawable(resources.getDrawable(R.drawable.olive_yes))
            //上报 申请 事件
            val imei = SPUtils.getInstance().imei
            val eventValue = HashMap<String, Any>()
            eventValue["Apply"] = "CustomerIMei:$imei"
            AppsFlyerLib.getInstance().trackEvent(applicationContext, "Apply", eventValue)

            //FaceBook事件埋点  Apply
            //            AppEventsLogger logger=AppEventsLogger.newLogger(OliveResultActivity.this);
            //            Bundle parameters = new Bundle();
            //            parameters.putString(AppEventsConstants.EVENT_NAME_INITIATED_CHECKOUT, "CustomerIMei:"+ BandaAppSP.getInstance().getImei());
            //            logger.logEvent(AppEventsConstants.EVENT_NAME_INITIATED_CHECKOUT,parameters);

        } else {
            mOliveappResultTextView!!.visibility = View.VISIBLE
            mOliveappResultTextView!!.text = getText(resources.getIdentifier("oliveapp_liveness_detection_fail_hint", "string", packageName))
            btnRetry!!.visibility = View.VISIBLE
            imageResult!!.visibility = View.VISIBLE
            toShare!!.visibility = View.GONE
            imageResult!!.setImageDrawable(resources.getDrawable(R.drawable.olive_no))
            btnBack!!.visibility = View.GONE
            mOliveResultll!!.visibility = View.GONE
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.olive_confirm ->
                //                intent = new Intent(OliveResultActivity.this, MyLoan.class);
                //                intent.putExtra("OliveFlag",100);
                //                startActivity(intent);
                finish()
            R.id.olive_to_share -> {
                //                intent=new Intent(OliveResultActivity.this, SharedActivity.class);
                //                intent.putExtra("OliveFlag",100);
                //                startActivity(intent);
            }
            R.id.btn_back -> finish()
            R.id.btn_retry -> {
                this@OliveResultActivity.startActivity(Intent(this@OliveResultActivity, OliveStartActivity::class.java))
                finish()
            }
        }
    }

    companion object {
        val TAG = OliveResultActivity::class.java.simpleName
    }
}
