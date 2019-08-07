package com.ibeilly.demo.dagger2.bean

import android.util.Log
import javax.inject.Inject

class Person {

    @Inject
    lateinit var car: Car

    override fun toString(): String {
        return car.run("啦啦啦")
    }

}


data class Car(var modelName: String) {

    fun run(msg: String): String {
        val result = "$modelName: $msg"
        Log.d("run", result)
        return result
    }
}