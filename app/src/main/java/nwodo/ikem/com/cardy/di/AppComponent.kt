package nwodo.ikem.com.cardy.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import nwodo.ikem.com.cardy.CardyApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class,
        AppModule::class,
        MainActivityModule::class,
        ViewModelModule::class]
)
interface AppComponent : AndroidInjector<CardyApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}