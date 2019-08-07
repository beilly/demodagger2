package com.ibeilly.demo.dagger2.dagger2

import dagger.Component
import dagger.Module
import dagger.Provides
import java.text.DateFormat
import java.text.SimpleDateFormat
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideDateFromater(): DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
}

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun getSingleFormater(): DateFormat
}