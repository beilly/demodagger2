package com.ibeilly.demo.dagger2.dagger2

import com.ibeilly.demo.dagger2.DemoActivity
import com.ibeilly.demo.dagger2.MainActivity
import com.ibeilly.demo.dagger2.bean.Car
import com.ibeilly.demo.dagger2.bean.Person
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope
import kotlin.random.Random

@Module
class PersonModule {


    @Provides
    fun providePerson(car: Car): Person =
        Person().apply {
            this.car = car
        }

    @Provides
    @BaseScope
    fun provideCar(): Car = Car("大众: ${Random.nextInt(100)}" )
}


@BaseScope
@Component(modules = [PersonModule::class], dependencies = [AppComponent::class])
interface PersonComponent {

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