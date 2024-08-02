package com.example.ncrtc

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class FeedbackActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var feedbackEditText: EditText
    private lateinit var sendButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        nameEditText = findViewById(R.id.name)
        feedbackEditText = findViewById(R.id.feedback)
        sendButton = findViewById(R.id.send)

        sendButton.setOnClickListener {
            sendFeedback()
        }
    }

    private fun sendFeedback() {
        val recipientEmail = "adityadattatre43@gmail.com"
        val subject = "Feedback from App User"
        val name = nameEditText.text.toString().trim()
        val feedback = feedbackEditText.text.toString().trim()

        if (name.isEmpty() || feedback.isEmpty()) {
            Toast.makeText(this, "Please enter your name and feedback", Toast.LENGTH_SHORT).show()
            return
        }

        val emailIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_EMAIL, arrayOf(recipientEmail))
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, "Name: $name\n\nFeedback:\n$feedback")
        }


        if (emailIntent.resolveActivity(packageManager) != null) {
            startActivity(Intent.createChooser(emailIntent, "Send Feedback via Email"))
        } else {
            Toast.makeText(this, "No email app found", Toast.LENGTH_SHORT).show()
        }
    }


}