package com.yinshan.happycash.view.me.view.impl

import android.support.v4.content.ContextCompat
import android.view.View

import com.yinshan.happycash.R
import com.yinshan.happycash.framework.BaseSingleActivity

/**
 * Created by huxin on 2018/3/30.
 */

class BorrowStrategyActivity : BaseSingleActivity(), View.OnClickListener {

    override fun bindTitle(): String {
        return resources.getString(R.string.help_center)
    }

    override fun bindDownLayout(): Int {
        return R.layout.activity_borrow_strategy
    }

    override fun secondInit() {
        lowestBg.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
    }

    override fun onClick(v: View) {

    }
}
