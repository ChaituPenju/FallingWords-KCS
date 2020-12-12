package com.chaitupenju.fallingwords.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chaitupenju.fallingwords.repositories.FallingWordsRepository

class FallingWordsViewModelFactory(private val fwRepository: FallingWordsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FallingWordsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FallingWordsViewModel(fwRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}