package nwodo.ikem.com.cardy.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CardDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCard(cardEntity: CardEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCard(cardEntity: CardEntity)

    @Delete
    suspend fun deleteCard(cardEntity: CardEntity)

    @Query("SELECT * FROM card_table ORDER BY id DESC")
    fun loadCards() : LiveData<List<CardEntity>>

    @Query("SELECT * FROM card_table WHERE id = :id")
    fun loadSingleCard(id: Int) : LiveData<CardEntity>



}
