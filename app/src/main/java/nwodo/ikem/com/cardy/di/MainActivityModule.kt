package nwodo.ikem.com.cardy.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import nwodo.ikem.com.cardy.MainActivity

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector(
        modules = [FragmentBuildersModule::class]
    )
    abstract fun contributeMainActivity() : MainActivity
}