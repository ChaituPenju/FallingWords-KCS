package com.chaitupenju.fallingwords.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chaitupenju.fallingwords.R
import com.chaitupenju.fallingwords.room.FallingWordsScoreEntity
import com.chaitupenju.fallingwords.utils.Utils
import kotlinx.android.synthetic.main.player_score_row.view.*

class FWScoreRecyclerAdapter(private var scores: List<FallingWordsScoreEntity>)
    : RecyclerView.Adapter<FWScoreRecyclerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.player_score_row, parent, false)

        return ViewHolder(v)
    }

    fun setScores(scores: List<FallingWordsScoreEntity>) {
        this.scores = scores
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val scoreData = scores[position]

        holder.name.text = scoreData.playerName
        holder.score.text = scoreData.playerScore.toString()
        holder.playedAt.text = Utils.convertMillisToDate(scoreData.gamePlayedAt)
    }

    override fun getItemCount(): Int {
        return scores.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.tv_row_name
        val score: TextView = itemView.tv_row_score
        val playedAt: TextView = itemView.tv_row_game_play_at
    }
}