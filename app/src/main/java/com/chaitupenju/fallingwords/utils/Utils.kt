package com.chaitupenju.fallingwords.utils

import android.content.res.Resources
import com.beust.klaxon.Klaxon
import com.chaitupenju.fallingwords.R
import com.chaitupenju.fallingwords.models.EngSpanWords
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

object Utils {

    private lateinit var englishSpanishWords: ArrayList<EngSpanWords>

    // Extract the words and parse json using klaxon library
    fun getEnglishSpanishList(r: Resources) {
        val englishSpanishJson = r.openRawResource(R.raw.words_v2)
            .bufferedReader().use { it.readText() }

        englishSpanishWords = Klaxon().parseArray<EngSpanWords>(englishSpanishJson)?.toMutableList() as ArrayList<EngSpanWords>
    }

    // Fill and return the array list of 4 random words(english and spanish)
    fun getRandomEnSpWords(): ArrayList<EngSpanWords> {
        val randomWords = ArrayList<EngSpanWords>(4)

        for (i in 0..3) {
            randomWords.add(englishSpanishWords.random())
        }

        return randomWords
    }

    // Helper function to convert milli seconds epoch to human readable date format
    fun convertMillisToDate(milliSec: Long): String {
        val sdf = SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy", Locale.ENGLISH)

        return sdf.format(Date(milliSec))
    }
}