package com.example.the_game_guess_the_number

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    private var min = 0
    private var max = 0
    private lateinit var textViewResult: TextView

    var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent: Intent? = result.data
                val result = intent?.getIntExtra("result", 0)
                textViewResult.text = getString(R.string.message_result) + result
            }
            if (result.resultCode == Activity.RESULT_CANCELED) {
                textViewResult.text = getString(R.string.activity_result_error)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewResult = findViewById(R.id.result)
        textViewResult.text = getString(R.string.editText_welcome_text)
    }

    fun onStartGameClick(view: android.view.View) {
        min = 0
        max = 0

        val upperEdit = findViewById<TextView>(R.id.upper)
        val lowerEdit = findViewById<TextView>(R.id.lower)

        val inputU = upperEdit?.text.toString().trim()
        val inputL = lowerEdit?.text.toString().trim()

        if (inputU.isNotBlank() && inputL.isNotBlank()) {
            min = inputL.toInt()
            max = inputU.toInt()
        }

        if (max > min) {
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("min", min)
            intent.putExtra("max", max)
            resultLauncher.launch(intent)
        } else {
            val text = getString(R.string.warning_empty_fields)
            val toast = Toast.makeText(this, text, Toast.LENGTH_SHORT)
            toast.show()
        }
    }

}