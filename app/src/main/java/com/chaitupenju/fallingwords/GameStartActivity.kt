package com.chaitupenju.fallingwords

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_game_start.*

class GameStartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_start)

        btn_start_game.setOnClickListener {
            // Intent to the game screen on this button click
        }
    }
}