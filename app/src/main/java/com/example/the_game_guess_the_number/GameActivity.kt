package com.example.the_game_guess_the_number

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class GameActivity : AppCompatActivity() {
    var max = 0
    var min = 0
    var mean = 0
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        min = intent.getIntExtra("min", 0)
        max = intent.getIntExtra("max", 0)

        textView = findViewById(R.id.field)

        mean = calcMean(max, min)
        textView.text = mean.toString()
    }

    fun onNoClick(view: android.view.View) {
        if (mean == min) {
            returnValue(min)
        }
        max = mean
        mean = calcMean(max, min)
        textView.text = mean.toString()
    }

    fun onYesClick(view: android.view.View) {
        if (mean + 1 == max) {
            returnValue(max)
        }
        min = mean
        mean = calcMean(max, min)
        textView.text = mean.toString()
    }

    private fun returnValue(res: Int) {
        val resultIntent = Intent()
        resultIntent.putExtra("result", res)
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }

    private fun calcMean(max: Int, min: Int): Int {
        return (max + min) / 2
    }
}