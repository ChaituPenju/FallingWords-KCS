package com.chaitupenju.fallingwords.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "falling_words_score")
@Parcelize
data class FallingWordsScoreEntity (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "player_name") val playerName: String,
    @ColumnInfo(name = "player_score") val playerScore: Int,
    @ColumnInfo(name = "game_played_at") val gamePlayedAt: Long
) : Parcelable {

}