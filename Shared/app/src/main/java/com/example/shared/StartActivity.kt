package com.example.shared

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Vibrator
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class StartActivity : AppCompatActivity() {

    private lateinit var vibrator: Vibrator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the layout for the activity
        setContentView(R.layout.activity_start)

        // Initialize the Vibrator service
        vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        // Find the Start button by its ID
        val startButton: Button = findViewById(R.id.startButton)

        // Set an onClickListener to the Start button
        startButton.setOnClickListener {
            // Navigate to MainActivity when the button is clicked
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            // Vibrate the phone
            vibratePhone()
        }
    }

    // Vibrate the phone for 100 milliseconds
    private fun vibratePhone() {
        if (vibrator.hasVibrator()) {
            vibrator.vibrate(100) // Vibrate for 100 milliseconds
        }
    }
}
