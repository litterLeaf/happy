package com.yinshan.happycash.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ExpandableListView
import android.widget.ListView


class CustomListView(context: Context, attrs: AttributeSet) : ListView(context, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE shr 2,
                View.MeasureSpec.AT_MOST)
        super.onMeasure(widthMeasureSpec, expandSpec)
    }
}
