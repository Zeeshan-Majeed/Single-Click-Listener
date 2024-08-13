package com.listner.helper.singleclicklistner.extensions

import android.os.SystemClock
import android.util.Log
import android.view.View
import com.listner.helper.singleclicklistner.extensions.Helper.lastClickTime

const val TAG = "click_listener_called"

object Executions {
    fun View.setOnOneClickListener(debounceTime: Long = 600L, action: () -> Unit) {
        this.setOnClickListener(object : View.OnClickListener {

            override fun onClick(v: View) {
                if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) {
                    Log.i(TAG, "Can't do anything on this click")
                    return
                } else {
                    Log.i(TAG, "Perform action on this click")
                    action()
                }
                lastClickTime = SystemClock.elapsedRealtime()
            }
        })
    }
}