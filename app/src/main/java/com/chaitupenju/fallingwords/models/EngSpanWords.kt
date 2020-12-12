package com.chaitupenju.fallingwords.models

import com.beust.klaxon.Json

class EngSpanWords(
    @Json(name = "text_eng")
    val englishText: String,

    @Json(name = "text_spa")
    val spanishText: String
)