package com.chaitupenju.fallingwords

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.chaitupenju.fallingwords.models.EngSpanWords
import com.chaitupenju.fallingwords.room.FallingWordsScoreEntity
import com.chaitupenju.fallingwords.utils.Constants
import com.chaitupenju.fallingwords.viewmodel.FallingWordsViewModel
import com.chaitupenju.fallingwords.viewmodel.FallingWordsViewModelFactory
import kotlinx.android.synthetic.main.activity_game_play.*

class GamePlayActivity : AppCompatActivity() {
    private lateinit var fallingWordAnimation: TranslateAnimation

    // variable to store all random words
    private lateinit var randomWordsList: List<EngSpanWords>

    // variable to store a random word(this will be the question word showed to player)
    private lateinit var randomWord: EngSpanWords

    // variable to store the answer options and control animation
    private var answerOptionPosition: Int = 0

    // variable to store correct answers count
    private var correctAnswerCount: Int = 0

    // variable to store total questions count
    private var totalQuestionsCount: Int = 1

    // variable to store player name which is entered in starting screen
    private lateinit var playerName: String

    // Create an instance of falling words view model
    private val fwViewModel: FallingWordsViewModel by viewModels {
        FallingWordsViewModelFactory((application as FallingWordsApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_play)

        val extras = intent.extras

        // Get the name of the player from game start activity and store it
        if (extras != null && extras.containsKey(Constants.PLAYER_NAME_EXTRA)) {
            playerName = extras.getString(Constants.PLAYER_NAME_EXTRA)!!
        }

        // Randomize the question and answers
        randomizeQuestionAnswers()

        // Start the animation
        startAnimation()

        btn_word_select.setOnClickListener {
            // validate selected answer here
            if (randomWordsList[answerOptionPosition].spanishText == randomWord.spanishText) {

                correctAnswerCount++
            } else {

            }

            totalQuestionsCount++

            // After answer is selected, show next question and answer options
            randomizeQuestionAnswers()

            // Cancel the current falling word animation and restart animation
            fallingWordAnimation.cancel()
            fallingWordAnimation.start()

            // Check for every button click, whether questions reached count 10
            checkAndStopGame()
        }
    }

    private fun randomizeQuestionAnswers() {
        // Get all random english spanish words list of size 5
        fwViewModel.getWordsJsonList(resources).observe(this) { randomWords ->
            randomWordsList = randomWords

            // Store the question english spanish word in a variable
            randomWord = randomWordsList.random()

            // Reset the answer position to zero
            answerOptionPosition = 0


            // Set question english text and answer spanish text with random object selected
            tv_english_word.text = "Question No. $totalQuestionsCount -> ${randomWord.englishText}"
            tv_spanish_falling_word.text = randomWordsList[0].spanishText
        }


    }

    // Function to check number of questions, stop game and send score to next activity
    private fun checkAndStopGame() {
        if (totalQuestionsCount == Constants.TOTAL_QUESTIONS) {
            Toast.makeText(
                this@GamePlayActivity,
                "Game Ended!",
                Toast.LENGTH_LONG
            )
                .show()

            fallingWordAnimation.cancel()

            val playerScore =
                FallingWordsScoreEntity(
                    0,
                    playerName,
                    correctAnswerCount,
                    System.currentTimeMillis()
                )

            val gameScoreIntent = Intent(this@GamePlayActivity, GameScoreActivity::class.java)
            gameScoreIntent.putExtra(Constants.PLAYER_SCORE_OBJECT_EXTRA, playerScore)

            startActivity(gameScoreIntent)
        }
    }


    private fun startAnimation() {

        // Initialize the transition animation from top to bottom
        fallingWordAnimation = TranslateAnimation(0f, 0f, 10f, 1000f)

        // Set the animation to infinite(so that we can stop wherever we want)
        fallingWordAnimation.repeatCount = Animation.INFINITE
        fallingWordAnimation.repeatMode = Animation.RESTART

        // Set the duration of the animation in milliseconds
        fallingWordAnimation.duration = Constants.FALLING_WORD_DURATION

        // Set the animation listener to perform falling word change and stop animation
        fallingWordAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
                println("Animation started!")
            }

            override fun onAnimationEnd(p0: Animation?) {
                println("Ending animation")

            }

            override fun onAnimationRepeat(p0: Animation?) {
                // If the answers word list finishes, that means user did not select any answer
                if (answerOptionPosition == randomWordsList.size - 1) {
                    totalQuestionsCount++
                    randomizeQuestionAnswers()

                    checkAndStopGame()
                } else {
                    // If 4 options are not shown, keep showing 4 options
                    tv_spanish_falling_word.text = randomWordsList[++answerOptionPosition].spanishText
                }
            }

        })

        // Start the falling word animation
        tv_spanish_falling_word.startAnimation(fallingWordAnimation)
    }
}