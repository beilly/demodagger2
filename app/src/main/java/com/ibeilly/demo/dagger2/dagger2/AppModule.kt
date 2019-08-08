package com.ibeilly.demo.dagger2.dagger2

import android.content.Context
import android.widget.Toast
import com.ibeilly.demo.dagger2.app
import com.ibeilly.demo.dagger2.bean.Car
import dagger.Component
import dagger.Module
import dagger.Provides
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Singleton
import kotlin.random.Random

@Module(subcomponents = [MainComponent::class])
class AppModule(var ctx: Context) {

    @Provides
    @Singleton
    fun provideDateFromater(): DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA)

    @Provides
    @Singleton
    fun provideToast(): Toast = Toast.makeText(app, "", Toast.LENGTH_SHORT)

    @Provides
    @Singleton
    fun provideContext() = ctx

    @Provides
    @Singleton
    fun provideCar() = Car("大众: ${Random.nextInt(100)}")
}

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun getSingleFormater(): DateFormat

    fun getSingleToast(): Toast

    fun getSingleCar(): Car

    fun mainComponent(): MainComponent.Builder
}