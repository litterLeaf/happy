package com.yinshan.happycash.widget.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v7.app.AppCompatDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
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
 *   文件描述：提醒需要哪些权限的类
 *    创建人：    admin
 *    创建时间：2018/8/24
 *
 */
class PerGuideDialogFragment : AppCompatDialogFragment() {

    private var mListener: GuideListener? = null

    interface GuideListener {
        fun guide()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //是否需要全屏，方法自定义
            setStyle()
    }

    fun setStyle() {
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.dialog)
    }

    override fun onStart() {
        super.onStart()
        val window = dialog.window
        val windowParams = window!!.attributes
        windowParams.dimAmount = 0.0f

        window.attributes = windowParams
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        val view = inflater.inflate(R.layout.dialog_permission_guide, null)
        val button = view.findViewById<View>(R.id.btn_confirm)
        button.setOnClickListener{
            if (mListener != null) {
                mListener!!.guide()
            }
            dismissAllowingStateLoss()
        }
        builder.setView(view)
        return builder.create()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is GuideListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement GuideListener.")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }
}
