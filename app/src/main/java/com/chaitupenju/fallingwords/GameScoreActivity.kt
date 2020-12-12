package com.chaitupenju.fallingwords

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.chaitupenju.fallingwords.adapters.FWScoreRecyclerAdapter
import com.chaitupenju.fallingwords.room.FallingWordsScoreEntity
import com.chaitupenju.fallingwords.utils.Constants
import com.chaitupenju.fallingwords.utils.Utils
import com.chaitupenju.fallingwords.viewmodel.FallingWordsViewModel
import com.chaitupenju.fallingwords.viewmodel.FallingWordsViewModelFactory
import kotlinx.android.synthetic.main.activity_game_score.*

class GameScoreActivity : AppCompatActivity() {

    // Instantiate falling words view model
    private val fwViewModel: FallingWordsViewModel by viewModels {
        FallingWordsViewModelFactory((application as FallingWordsApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_score)

        val extras = intent.extras

        // Get the score data bundle from previous activity and show to player
        if (extras != null && extras.containsKey(Constants.PLAYER_SCORE_OBJECT_EXTRA)) {
            val fwScore: FallingWordsScoreEntity = extras.getParcelable(Constants.PLAYER_SCORE_OBJECT_EXTRA)!!
            fwViewModel.insertScore(fwScore)

            tv_player_name.append(fwScore.playerName)
            tv_player_score.append(fwScore.playerScore.toString())
            tv_game_played_time.append(Utils.convertMillisToDate(fwScore.gamePlayedAt))
        }

        setupScoresList()
    }

    private fun setupScoresList() {
        // Initialize the scores recycler adapter and set adapter to recycler view
        val scoresAdapter = FWScoreRecyclerAdapter(listOf())
        rv_player_scores.adapter = scoresAdapter

        // Observe the stored player scores from room db and set recyclerview with player scores list
        fwViewModel.getAllScores().observe(this) { playerScores ->
            // Show scores in descending order of game played time
            scoresAdapter.setScores(playerScores.sortedByDescending { it.gamePlayedAt })
        }
    }
}