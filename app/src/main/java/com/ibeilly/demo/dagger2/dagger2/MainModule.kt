package com.ibeilly.demo.dagger2.dagger2

import com.ibeilly.demo.dagger2.MainActivity
import com.ibeilly.demo.dagger2.bean.Car
import com.ibeilly.demo.dagger2.bean.Dog
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import javax.inject.Named

@Module
class MainModule {

    @Provides
    @ActivityScope
    fun provideDog(@Named("dogName") dogName: String, car: Car) = Dog().apply {
        this.name = dogName
        this.car = car
    }
}

@ActivityScope
@Subcomponent(modules = [MainModule::class])
interface MainComponent {

    fun injectMain(activity: MainActivity)

    @Subcomponent.Builder
    interface Builder {

        @BindsInstance
        fun dogName(@Named("dogName") dogName: String): Builder

        fun build(): MainComponent
    }
}