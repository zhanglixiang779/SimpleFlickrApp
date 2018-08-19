package com.example.gavinzhang.simpleflickrapp.ui.master

import com.example.gavinzhang.simpleflickrapp.ui.master.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class FlickrApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out FlickrApplication> =
        DaggerAppComponent.builder().create(this)

}