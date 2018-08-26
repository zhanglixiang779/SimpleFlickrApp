package com.example.gavinzhang.simpleflickrapp.ui.master.di

import com.example.gavinzhang.simpleflickrapp.ui.master.ui.activities.MasterActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesBindingModule {

    @ContributesAndroidInjector(modules = [MasterActivityModule::class,
        FragmentsBindingModule::class])
    abstract fun masterActivity(): MasterActivity

}