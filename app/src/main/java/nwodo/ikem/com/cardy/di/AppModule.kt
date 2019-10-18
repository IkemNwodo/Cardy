package nwodo.ikem.com.cardy.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import nwodo.ikem.com.cardy.db.CardyDb
import nwodo.ikem.com.cardy.db.CardDao
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun providesCardyDb(application: Application): CardyDb{
        return Room
            .databaseBuilder(application, CardyDb::class.java, "cardy_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providesOneCardDao(db: CardyDb): CardDao{
        return db.oneCardDao()
    }
}