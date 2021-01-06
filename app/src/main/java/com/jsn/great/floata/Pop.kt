package com.jsn.great.floata

import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.PopupWindow
import androidx.core.content.ContextCompat
import com.jsn.great.App
import com.jsn.great.R

class Pop :PopupWindow {

    lateinit var context: Context

    constructor(context: Context){
        this.context=context
    }

    private val ivwidth: Int = dp2px(40)
    private val size: Int = dp2px(50)

    init {
        ImageView(context).apply {
            val iv=this
            iv.setMinimumWidth(size)
            iv.setMinimumHeight(size)
            iv.setImageDrawable(ContextCompat.getDrawable(App.instance as Context, R.mipmap.ic_floatball4))
            contentView = iv
            setWidth(size)
            setHeight(size)
            isFocusable = false
            isOutsideTouchable = true
            setBackgroundDrawable(ColorDrawable(0x00000000))
        }
        setTouchInterceptor { v, event ->
            event.rawX
            true
        }
    }


}

fun dp2px(dp: Int): Int {
    val density = Resources.getSystem().displayMetrics.density
    return (density * dp + 0.5f).toInt()
}

fun px2dp(px: Int): Int {
    val density = Resources.getSystem().displayMetrics.density
    return (px / density + 0.5f).toInt()
}

fun getResDrawable(name: String?, app: Application? = App.instance): Drawable? {
    return app!!.getResources().getDrawable(
        app.getResources().getIdentifier(
            name, "mipmap",
            app.getPackageName()
        )
    )
}