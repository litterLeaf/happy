package com.yinshan.happycash.utils

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
 *    创建时间：2018/8/28
 *
 */
 class  LockUtils {
    companion object Instance{
        private var lastClickTime: Long = 0
        @Synchronized
        fun  isFastClick(): Boolean {
            val time = System.currentTimeMillis()
            if (time - lastClickTime < 2000) {
                return true
            }
            lastClickTime = time
            return false
        }
    }
}