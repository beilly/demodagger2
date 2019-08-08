package com.ibeilly.demo.dagger2.bean

import android.util.Log
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

class Person @Inject constructor() {

    @Inject
    lateinit var car: Car

    override fun toString(): String {
        return car.run("人")
    }

}

class Dog {

    @Inject
    @field:Named("dogName")
    lateinit var name: String

    @Inject
    lateinit var car: Car

    override fun toString(): String {
        return car.run("$name :啦啦啦")
    }

}

data class Car(var modelName: String) {

    fun run(who: String): String {
        val result = "$who: 开车 $modelName"
        Log.d("run", result)
        return result
    }
}