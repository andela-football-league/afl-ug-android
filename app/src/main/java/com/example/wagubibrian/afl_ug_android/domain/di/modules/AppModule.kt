package com.example.wagubibrian.afl_ug_android.domain.di.modules

import android.app.Application
import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import com.example.wagubibrian.afl_ug_android.R
import com.example.wagubibrian.afl_ug_android.domain.data.local.Database
import com.example.wagubibrian.afl_ug_android.domain.data.repository.implementations.OfflineStore
import com.example.wagubibrian.afl_ug_android.domain.data.repository.implementations.Repository
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executors
import javax.inject.Singleton



@Module
class AppModule (val app: Application) {

    @Provides
    @Singleton
    fun provideApplication() = app

    @Provides
    @Singleton
    fun provideDatabase(app: Application): Database {
        lateinit var database: Database
        database = Room.databaseBuilder(
            app,
            Database::class.java, app.getString(R.string.database_name))
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    Executors.newSingleThreadScheduledExecutor()
                        .execute(
                            Runnable {
                                Database.getSeedTeams().forEach {
                                    database.teamDao().insertAll(it)
                                }
                                Database.getSeedMatches().forEach {
                                    database.matchDao().insertAll(it)
                                }
                                Database.getSeedPlayers().forEach {
                                    database.playerDao().insertAll(it)
                                }
                            })
                }
            })
            .build()
        return database
    }

    @Provides
    @Singleton
    fun provideOfflineStore(database: Database) = OfflineStore(database)

    @Provides
    @Singleton
    fun provideRepository(offlineStore: OfflineStore, database: Database) = Repository(offlineStore)
}
