package com.example.ncrtc

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn1: Button = findViewById(R.id.button)
        val btn2: Button = findViewById(R.id.button2)
        val btn3: Button = findViewById(R.id.button4)
        val btn4: Button = findViewById(R.id.button5)
        val btn5: Button = findViewById(R.id.button6)
        val btn6: Button = findViewById(R.id.button7)
        val btn7: Button = findViewById(R.id.button8)
        val btn8: Button = findViewById(R.id.button9)
        val btn9:Button=findViewById(R.id.feedback)
        val btn10:Button=findViewById(R.id.pathfinder)
        val btn11:Button=findViewById(R.id.button10)
        val btn12:Button=findViewById(R.id.button11)
        btn1.setOnClickListener {
            val stationInfo = StationInfo(
                name = "SAHIBABAD",
                feedermodes = listOf("Metro – Blue Line Vaisali ( 3 km)", "Rapido – 2w Taxi, Auto, Cabs"),
                busroutes = listOf("To Govind Puri", "To Kanoja Gaon", "To Govind puram", "To ALT Centre"),
                parkandriding = listOf("2 wheelers - 50", "Cars - 70"),
                evcharging = listOf("Slow Charging (Car)", "Battery Swapping ( 2 Wh, Autorickshaw)"),
                imageResId = R.drawable.image9
            )
            onDisplayDataActivity(stationInfo)
        }

        btn2.setOnClickListener {
            val stationInfo = StationInfo(
                name = "GHAZIABAD",
                feedermodes = listOf("Metro – Red Line Shaheed Sthal ( 300 m)", "Rapido – 3w Taxi, Auto, Cabs"),
                busroutes = listOf("To Sanjay Nagar/ALT", "To Massuri", "To Govind puram", "To Purana Bus Adda,", "To GovindPuri", "To Mondola", "To Kanoja Gaon"),
                parkandriding = listOf("2 wheelers - 25", "Cars - 50"),
                evcharging = listOf("fast Charging (Not installed yet)", "Battery Swapping (Not installed yet)"),
                imageResId = R.drawable.image10
            )
            onDisplayDataActivity(stationInfo)
        }

        btn3.setOnClickListener {
            val stationInfo = StationInfo(
                name = "GULDHAR",
                feedermodes = listOf("Metro – No", "Rapido – No"),
                busroutes = listOf("To GovindPuri", "To Kanoja Gaon"),
                parkandriding = listOf("2 wheelers - 177", "Cars - 77"),
                evcharging = listOf("fast Charging (Not installed yet)", "Battery Swapping (Not installed yet)"),
                imageResId = R.drawable.guldhar
            )
            onDisplayDataActivity(stationInfo)
        }

        btn4.setOnClickListener {
            val stationInfo = StationInfo(
                name = "DUHAI",
                feedermodes = listOf("Metro – No", "Rapido – No"),
                busroutes = listOf("To GovindPuri", "To Kanoja Gaon"),
                parkandriding = listOf("2 wheelers - 270", "Cars - 14","buses - 12"),
                evcharging = listOf("fast Charging (Not installed yet)", "Battery Swapping (Not installed yet)"),
                imageResId = R.drawable.duhai
            )
            onDisplayDataActivity(stationInfo)
        }

        btn5.setOnClickListener {
            val stationInfo = StationInfo(
                name = "DUHAI-DEPOT",
                feedermodes = listOf("Metro – No", "Rapido – No"),
                busroutes = listOf(""),
                parkandriding = listOf("2 wheelers - 2", "Cars - 3"),
                evcharging = listOf("fast Charging (Not installed yet)", "Battery Swapping (Not installed yet)"),
                imageResId = R.drawable.duhaidepot
            )
            onDisplayDataActivity(stationInfo)
        }

        btn6.setOnClickListener {
            val stationInfo = StationInfo(
                name = "MURADNAGAR",
                feedermodes = listOf("Metro – No", "Rapido – No"),
                busroutes = listOf("To Govind Puri"),
                parkandriding = listOf("2 wheelers - 447", "Cars - 86"),
                evcharging = listOf("fast Charging (Not installed yet)", "Battery Swapping (Not installed yet)"),
                imageResId = R.drawable.muradnagar
            )
            onDisplayDataActivity(stationInfo)
        }

        btn7.setOnClickListener {
            val stationInfo = StationInfo(
                name = "MODINAGAR(N)",
                feedermodes = listOf("Metro – No", "Rapido – No"),
                busroutes = listOf("To Govind Puri"),
                parkandriding = listOf("2 wheelers - 230", "Cars - 41"),
                evcharging = listOf("fast Charging (Not installed yet)", "Battery Swapping (Not installed yet)"),
                imageResId = R.drawable.modinagarn
            )
            onDisplayDataActivity(stationInfo)
        }

        btn8.setOnClickListener {
            val stationInfo = StationInfo(
                name = "MODINAGAR(S)",
                feedermodes = listOf("Metro – No", "Rapido – No"),
                busroutes = listOf("To Govind Puri"),
                parkandriding = listOf("2 wheelers - 159", "Cars - 12"),
                evcharging = listOf("fast Charging (Not installed yet)", "Battery Swapping (Not installed yet)"),
                imageResId = R.drawable.modinagars
            )
            onDisplayDataActivity(stationInfo)
        }
        btn9.setOnClickListener{
            val intent=Intent(this,FeedbackActivity::class.java)
            startActivity(intent)

        }
        btn10.setOnClickListener{
            val intent=Intent(this,PathfinderActivity::class.java)
            startActivity(intent)


        }
btn11.setOnClickListener{

    val intent=Intent(this,impact::class.java)
    startActivity(intent)
}
        btn12.setOnClickListener {
            val intent=Intent(this,features::class.java)
            startActivity(intent)
        }

    }

    private fun onDisplayDataActivity(stationInfo: StationInfo) {
        val intent = Intent(this, DisplayDataActivity::class.java)
        val dataJson = Gson().toJson(stationInfo)
        intent.putExtra("EXTRA_DATA", dataJson)
        startActivity(intent)
    }
}
