package nwodo.ikem.com.cardy.repository

import nwodo.ikem.com.cardy.db.CardEntity
import nwodo.ikem.com.cardy.db.CardDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CardRepository @Inject constructor(private val cardDao: CardDao){


    suspend fun insertCard(cardEntity: CardEntity) = cardDao.insertCard(cardEntity)

    suspend fun updateCard(cardEntity: CardEntity) = cardDao.updateCard(cardEntity)

    suspend fun deleteCard(cardEntity: CardEntity) = cardDao.deleteCard(cardEntity)

    fun loadCards() = cardDao.loadCards()

    fun loadSingleCard(cardId: Int) = cardDao.loadSingleCard(cardId)
}