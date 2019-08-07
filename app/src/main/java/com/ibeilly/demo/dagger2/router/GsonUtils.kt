package com.ibeilly.demo.dagger2.router

import com.google.gson.Gson
import com.google.gson.GsonBuilder

val JSON = Gson()

val JPrint = GsonBuilder()
    .setPrettyPrinting()
    .create()