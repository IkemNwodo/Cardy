package nwodo.ikem.com.cardy.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import nwodo.ikem.com.cardy.ui.add.AddCardFragmentViewModel
import nwodo.ikem.com.cardy.ui.list.CardyListFragmentViewModel
import nwodo.ikem.com.cardy.viewmodel.CardyViewModelFactory

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CardyListFragmentViewModel::class)
    abstract fun bindCardyListFragmentViewModel(cardyListFragmentViewModel: CardyListFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddCardFragmentViewModel::class)
    abstract fun bindAddCardFragmentViewModel(addCardFragmentViewModel: AddCardFragmentViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: CardyViewModelFactory): ViewModelProvider.Factory
}