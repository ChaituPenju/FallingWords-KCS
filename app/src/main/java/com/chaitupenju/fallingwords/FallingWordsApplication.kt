package com.chaitupenju.fallingwords

import android.app.Application
import com.chaitupenju.fallingwords.repositories.FallingWordsRepository
import com.chaitupenju.fallingwords.room.FallingWordsDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class FallingWordsApplication: Application() {

    // Instantiate the database and repository variables in our application class
    private val fallingWordsDatabase by lazy { FallingWordsDatabase.getDatabase(this) }
    val repository by lazy { FallingWordsRepository(fallingWordsDatabase.fwScoreDao()) }
}