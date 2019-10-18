package nwodo.ikem.com.cardy

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import nwodo.ikem.com.cardy.di.DaggerAppComponent

class CardyApplication : DaggerApplication(){

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        return DaggerAppComponent.builder().application(this).build()
    }
}