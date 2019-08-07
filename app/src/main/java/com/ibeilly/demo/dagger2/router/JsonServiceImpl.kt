package com.ibeilly.demo.dagger2.router

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.service.SerializationService
import java.lang.reflect.Type


@Route(path = "/app/json")
class JsonServiceImpl : SerializationService {
    override fun <T : Any?> parseObject(input: String?, clazz: Type?): T {
        return JSON.fromJson(input, clazz)
    }

    override fun init(context: Context) {

    }

    override fun <T> json2Object(text: String, clazz: Class<T>): T {
        return parseObject(text, clazz)
    }

    override fun object2Json(instance: Any): String {
        return JSON.toJson(instance)
    }
}