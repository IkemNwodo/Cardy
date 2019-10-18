package nwodo.ikem.com.cardy.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CardEntity::class], version = 1)
abstract class CardyDb : RoomDatabase(){

    abstract fun oneCardDao() : CardDao
}