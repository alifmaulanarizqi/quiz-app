package com.alifmaulanarizqi.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val tvName: TextView = findViewById(R.id.tvName)
        val tvScore: TextView = findViewById(R.id.tvScore)
        val btnFinish: Button = findViewById(R.id.btnFinish)

        val username = intent.getStringExtra(Constants.USER_NAME_KEY)
        tvName.text = username

        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTION_KEY, 0)
        val totalScores = intent.getIntExtra(Constants.SCORE_KEY, 0)

        tvScore.text = "Your score is $totalScores out of $totalQuestions"

        btnFinish.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

    }
}