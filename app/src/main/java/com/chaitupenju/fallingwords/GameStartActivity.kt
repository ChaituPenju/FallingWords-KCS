package com.chaitupenju.fallingwords

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chaitupenju.fallingwords.utils.Constants
import kotlinx.android.synthetic.main.activity_game_start.*

class GameStartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_start)

        btn_start_game.setOnClickListener {
            // Intent to the game screen on this button click
            val gamePlayIntent = Intent(this@GameStartActivity, GamePlayActivity::class.java)
            gamePlayIntent.putExtra(Constants.PLAYER_NAME_EXTRA, et_player_name.text.toString())

            startActivity(gamePlayIntent)
        }
    }
}