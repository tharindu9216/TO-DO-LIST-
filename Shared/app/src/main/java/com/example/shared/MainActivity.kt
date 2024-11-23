package com.example.shared

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Vibrator
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.shared.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var vibrator: Vibrator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("NoteData", Context.MODE_PRIVATE)
        vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        // Save note button click listener
        binding.saveNoteButton.setOnClickListener {
            vibratePhone()  // Add vibration
            val note = binding.notesEditText.text.toString()
            if (note.isNotBlank()) {
                val currentDateTime = getCurrentDateTime()
                val noteWithDateTime = "$note\n\nSaved on: $currentDateTime"
                sharedPreferences.edit().putString("note", noteWithDateTime).apply()
                Toast.makeText(this, "Note Stored Successfully", Toast.LENGTH_SHORT).show()
                binding.notesEditText.text.clear()
            } else {
                Toast.makeText(this, "Please enter a note", Toast.LENGTH_SHORT).show()
            }
        }

        // Display note button click listener
        binding.displayNoteButton.setOnClickListener {
            vibratePhone()  // Add vibration
            val storedNote = sharedPreferences.getString("note", "")
            binding.noteTextView.text = storedNote ?: "No note found"
        }

        // Update note button click listener
        binding.updateNoteButton.setOnClickListener {
            vibratePhone()  // Add vibration
            val newNote = binding.notesEditText.text.toString()
            if (newNote.isNotBlank()) {
                val currentDateTime = getCurrentDateTime()
                val noteWithDateTime = "$newNote\n\nUpdated on: $currentDateTime"
                sharedPreferences.edit().putString("note", noteWithDateTime).apply()
                Toast.makeText(this, "Note Updated Successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please enter a note to update", Toast.LENGTH_SHORT).show()
            }
        }

        // Delete note button click listener
        binding.deleteNoteButton.setOnClickListener {
            vibratePhone()  // Add vibration
            sharedPreferences.edit().remove("note").apply()
            binding.noteTextView.text = "Your note will appear here"
            Toast.makeText(this, "Note Deleted Successfully", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getCurrentDateTime(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return sdf.format(Date())
    }

    private fun vibratePhone() {
        if (vibrator.hasVibrator()) {
            vibrator.vibrate(100) // Vibrate for 100 milliseconds
        }
    }
}