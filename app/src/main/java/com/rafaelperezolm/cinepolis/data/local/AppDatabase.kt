package com.rafaelperezolm.cinepolis.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rafaelperezolm.cinepolis.data.entities.Loginfo
import com.rafaelperezolm.cinepolis.data.entities.Movy
import com.rafaelperezolm.cinepolis.data.entities.Profile

@Database(entities = [Movy::class, Loginfo::class, Profile::class], version = 1, exportSchema = false)
@TypeConverters(ConverterMedia::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun characterDao(): CinepolisDAO

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "cinepolisdb")
                .fallbackToDestructiveMigration()
                .build()
    }

}