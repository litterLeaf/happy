package com.yinshan.happycash.widget.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.yinshan.happycash.R

/**
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 *        ┃　　　┃   兽神保佑
 *        ┃　　　┃   代码无BUG！
 *        ┃　　　┗━━━┓
 *        ┃　　　　　　　┣┓
 *        ┃　　　　　　　┏┛
 *        ┗┓┓┏━┳┓┏┛
 *           ┃┫┫　┃┫┫
 *           ┗┻┛　┗┻┛
 *
 *   文件描述：
 *    创建人：    admin
 *    创建时间：2018/8/24
 *
 */
class CheckPermissionDialog(context: Context, private val mOnConfirmClickListener: OnConfirmClickListener) : Dialog(context, R.style.CancelAssignDialogStyle), View.OnClickListener {
    private var contentTv: TextView? = null
    private var confirmBtn: Button? = null
    private var cancelBtn: Button? = null

    override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_permission_check)
        contentTv = findViewById(R.id.check_permission_text) as TextView
        confirmBtn = findViewById(R.id.btn_confirm) as Button
        cancelBtn = findViewById(R.id.btn_cancel) as Button
        confirmBtn!!.setOnClickListener(this)
        cancelBtn!!.setOnClickListener(this)

        setCancelable(true)
        setCanceledOnTouchOutside(true)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_cancel -> dismiss()
            R.id.btn_confirm -> {
                dismiss()
                mOnConfirmClickListener.onConfirmClick()
            }
        }
    }

    fun setCancelShow(isShow: Boolean): CheckPermissionDialog {
        if (!isShow) {
            cancelBtn!!.visibility = View.GONE
        }
        return this
    }

    fun setMessageText(message: String): CheckPermissionDialog {
        contentTv!!.text = message
        return this
    }

    interface OnConfirmClickListener {
        fun onConfirmClick()
    }
}
