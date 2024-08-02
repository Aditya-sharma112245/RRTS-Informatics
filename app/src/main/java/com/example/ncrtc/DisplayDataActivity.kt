package com.example.ncrtc

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson

class DisplayDataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_data)

        val name: TextView = findViewById(R.id.textView)
        val photo: ImageView = findViewById(R.id.imageView)
        val feederLabel: TextView = findViewById(R.id.feedermodes_label)
        val feeder: TextView = findViewById(R.id.feedermodes)
        val busLabel: TextView = findViewById(R.id.busroutes_label)
        val bus: TextView = findViewById(R.id.busroutes)
        val parkLabel: TextView = findViewById(R.id.parkandriding_label)
        val park: TextView = findViewById(R.id.parkandriding)
        val evLabel: TextView = findViewById(R.id.evcharging_label)
        val ev: TextView = findViewById(R.id.evcharging)

        val dataJson = intent.getStringExtra("EXTRA_DATA")
        val stationInfo = Gson().fromJson(dataJson, StationInfo::class.java)

        name.text = stationInfo.name
        feederLabel.text = "Feeder Modes:"
        feeder.text = stationInfo.feedermodes.joinToString("\n")
        busLabel.text = "Bus Routes:"
        bus.text = stationInfo.busroutes.joinToString("\n")
        parkLabel.text = "Park and Ride:"
        park.text = stationInfo.parkandriding.joinToString("\n")
        evLabel.text = "EV Charging:"
        ev.text = stationInfo.evcharging.joinToString("\n")
        photo.setImageResource(stationInfo.imageResId)
    }
}

