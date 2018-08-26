package com.example.gavinzhang.simpleflickrapp.ui.master.di

import com.example.gavinzhang.simpleflickrapp.ui.master.FlickrApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class,
    ActivitiesBindingModule::class])
interface AppComponent : AndroidInjector<FlickrApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<FlickrApplication>()
}