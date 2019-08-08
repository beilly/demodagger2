package com.ibeilly.demo.dagger2.dagger2

import com.ibeilly.demo.dagger2.DemoActivity
import com.ibeilly.demo.dagger2.MainActivity
import com.ibeilly.demo.dagger2.bean.Car
import com.ibeilly.demo.dagger2.bean.Dog
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Scope
import kotlin.random.Random

@Module
class CarModule {

    @Provides
    @BaseScope
    fun provideCar() = Car("大众: ${Random.nextInt(100)}")

    @Provides
    @BaseScope
    @Named("dogName")
    fun provideDogName() = "哈士奇: ${Random.nextInt(100)}"

    @Provides
    @BaseScope
    fun provideDog(@Named("dogName") dogName: String, car: Car) = Dog().apply {
        this.name = dogName
        this.car = car
    }
}


@BaseScope
@Component(modules = [CarModule::class], dependencies = [AppComponent::class])
interface CarComponent {

    fun inject(activity: MainActivity)

    fun inject(activity: DemoActivity)
}


/**
 * Scope 标注是Scope
 * @Retention(RUNTIME) 运行时级别
 */
@Scope
@Retention
annotation class BaseScope