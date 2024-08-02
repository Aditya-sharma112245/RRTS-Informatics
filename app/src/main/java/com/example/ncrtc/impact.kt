package com.example.ncrtc

import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class impact : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_impact)

        val textView = findViewById<TextView>(R.id.textView7)

        // Create a LinearGradient shader
        val textShader = LinearGradient(
            0f, 0f, textView.paint.measureText(textView.text.toString()), 0f,
            intArrayOf(
                ContextCompat.getColor(this, R.color.blue),
                ContextCompat.getColor(this, R.color.green)
            ),
            null, Shader.TileMode.CLAMP
        )

        // Set the shader on the TextView's paint
        textView.paint.shader = textShader
    }
}
