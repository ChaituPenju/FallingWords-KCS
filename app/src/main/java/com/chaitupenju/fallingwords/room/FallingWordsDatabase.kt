package com.chaitupenju.fallingwords.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [FallingWordsScoreEntity::class], version = 1, exportSchema = false)
abstract class FallingWordsDatabase : RoomDatabase() {

    abstract fun fwScoreDao(): FallingWordsScoreDao

    // Create a singleton instance of custom room database class
    companion object {
        private var INSTANCE: FallingWordsDatabase? = null

        fun getDatabase(context: Context): FallingWordsDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FallingWordsDatabase::class.java,
                    "words_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}