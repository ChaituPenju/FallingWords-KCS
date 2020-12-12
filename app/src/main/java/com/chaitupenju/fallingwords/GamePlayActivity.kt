package com.chaitupenju.fallingwords

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import androidx.appcompat.app.AppCompatActivity
import com.chaitupenju.fallingwords.utils.Constants
import kotlinx.android.synthetic.main.activity_game_play.*

class GamePlayActivity : AppCompatActivity() {

    private lateinit var fallingWordAnimation: TranslateAnimation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_play)


        // Start the animation
        startAnimation()

        btn_word_select.setOnClickListener {
            // validate selected answer here
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

            }

        })

        // Start the falling word animation
        tv_spanish_falling_word.startAnimation(fallingWordAnimation)
    }
}