package com.chaitupenju.fallingwords.viewmodel

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chaitupenju.fallingwords.models.EngSpanWords
import com.chaitupenju.fallingwords.repositories.FallingWordsRepository
import com.chaitupenju.fallingwords.room.FallingWordsScoreEntity
import kotlinx.coroutines.launch

class FallingWordsViewModel(private val fwRepository: FallingWordsRepository): ViewModel() {

    // Get list of 4 random (english and spanish words objects) livedata from repository
    fun getWordsJsonList(r: Resources): LiveData<List<EngSpanWords>> {
        return fwRepository.wordsJsonList(r)
    }

    // Get a list of all scores stored in room db
    fun getAllScores(): LiveData<List<FallingWordsScoreEntity>> {
        return fwRepository.allScores
    }

    // Insert score in room db
    fun insertScore(fwScore: FallingWordsScoreEntity) = viewModelScope.launch {
        fwRepository.insert(fwScore)
    }

}