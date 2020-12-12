package com.chaitupenju.fallingwords.room

import kotlinx.coroutines.flow.Flow
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FallingWordsScoreDao {
    @Query("SELECT * from falling_words_score ORDER BY id")
    fun getAllScores(): Flow<List<FallingWordsScoreEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(fwScore: FallingWordsScoreEntity)

    @Query("DELETE FROM falling_words_score")
    suspend fun deleteAll()
}