package com.ibeilly.demo.dagger2

import android.app.Application
import android.content.Context
import android.os.Handler
import android.widget.Toast
import com.alibaba.android.arouter.launcher.ARouter
import com.ibeilly.demo.dagger2.dagger2.AppComponent
import com.ibeilly.demo.dagger2.dagger2.DaggerAppComponent
import com.ibeilly.demo.dagger2.kotlin.getPropertyValue
import com.ibeilly.demo.dagger2.kotlin.invokeMethod

class App : Application() {
    lateinit var appComponent: AppComponent

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        app = this
    }

    override fun onCreate() {
        super.onCreate()
        initDagger()
        initARouter()
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.create()
    }

    private fun initARouter() {
        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog()     // 打印日志
            ARouter.openDebug()   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this)
    }
}

lateinit var app: App

val handler = Handler()


fun Any.pretty(name: String, method: String = "toString", vararg args: Any?): String = run {

    val value = getPropertyValue(name)?.invokeMethod(method, *args)

    "$name: ${value?.toString()} , hashCode: ${value?.hashCode()}"
}

fun Toast.show(msg: String?) {
    if (msg.isNullOrEmpty()) {
        return
    } else {
        cancel()
        handler.postDelayed({
            setText(msg)
            show()
        }, 100)
    }
}