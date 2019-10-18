package nwodo.ikem.com.cardy.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import nwodo.ikem.com.cardy.ui.add.AddCardFragment
import nwodo.ikem.com.cardy.ui.list.CardyListFragment

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeAddCardFragment(): AddCardFragment

    @ContributesAndroidInjector
    abstract fun contributeCardyListFragment(): CardyListFragment
}