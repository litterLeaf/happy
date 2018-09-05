package com.yinshan.happycash.view.me.view.impl

import android.support.v4.content.ContextCompat

import com.yinshan.happycash.R
import com.yinshan.happycash.framework.BaseSingleActivity


/**
 * Created by huxin on 2018/3/20.
 */

class AboutActivity : BaseSingleActivity() {

    override fun bindTitle(): String {
        return resources.getString(R.string.about_happy_cash)
    }

    override fun bindDownLayout(): Int {
        return R.layout.activity_about
    }

    override fun secondInit() {
        lowestBg.setBackgroundColor(ContextCompat.getColor(this, R.color.app_yellow))
    }
}
