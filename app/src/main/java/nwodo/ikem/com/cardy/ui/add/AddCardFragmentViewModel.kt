package nwodo.ikem.com.cardy.ui.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nwodo.ikem.com.cardy.db.CardEntity
import nwodo.ikem.com.cardy.repository.CardRepository
import javax.inject.Inject

class AddCardFragmentViewModel @Inject constructor(val cardRepository: CardRepository): ViewModel() {

    fun insertCard(cardEntity: CardEntity) = viewModelScope.launch(Dispatchers.IO){
        cardRepository.insertCard(cardEntity)
    }

    fun updateCard(cardEntity: CardEntity) = viewModelScope.launch(Dispatchers.IO){
        cardRepository.updateCard(cardEntity)
    }

    fun loadSingleCard(cardId: Int) = cardRepository.loadSingleCard(cardId)
}
