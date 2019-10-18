package nwodo.ikem.com.cardy.ui.list

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nwodo.ikem.com.cardy.db.CardEntity
import nwodo.ikem.com.cardy.repository.CardRepository
import javax.inject.Inject

class CardyListFragmentViewModel @Inject constructor(val cardRepository: CardRepository): ViewModel() {

    val loadCards = cardRepository.loadCards()

    fun deleteCard(cardEntity: CardEntity) = viewModelScope.launch(Dispatchers.IO){
        cardRepository.deleteCard(cardEntity)
    }

    fun loadSingleCard(cardId: Int) = cardRepository.loadSingleCard(cardId)

}
