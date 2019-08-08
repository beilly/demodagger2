package com.ibeilly.demo.dagger2.dagger2

import com.ibeilly.demo.dagger2.DemoActivity
import com.ibeilly.demo.dagger2.bean.Person
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class PersonModule {

    @Provides
    @Named("vip")
    @BaseScope
    fun providePerson() = Person()

}


@BaseScope
@Component(modules = [PersonModule::class], dependencies = [AppComponent::class])
interface CarComponent {

    fun inject(activity: DemoActivity)
}
