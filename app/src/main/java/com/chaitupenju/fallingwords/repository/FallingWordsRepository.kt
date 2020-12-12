package com.chaitupenju.fallingwords.repositories

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.chaitupenju.fallingwords.models.EngSpanWords
import com.chaitupenju.fallingwords.room.FallingWordsScoreDao
import com.chaitupenju.fallingwords.room.FallingWordsScoreEntity
import com.chaitupenju.fallingwords.utils.Utils

class FallingWordsRepository(private val fwScoreDao: FallingWordsScoreDao) {
    // Get all list of scores as live data
    val allScores = fwScoreDao.getAllScores().asLiveData()

    private val _engSpaWordsList = MutableLiveData<List<EngSpanWords>>()

    // Returns a list of random words live data
    fun wordsJsonList(r: Resources): LiveData<List<EngSpanWords>> {
        Utils.getEnglishSpanishList(r)
        _engSpaWordsList.value = Utils.getRandomEnSpWords()

        return _engSpaWordsList
    }

    // Insert score
    suspend fun insert(fwScore: FallingWordsScoreEntity) {
        fwScoreDao.insert(fwScore)
    }
}