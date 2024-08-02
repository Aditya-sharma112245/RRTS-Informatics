package com.example.ncrtc

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView

class PathfinderActivity : AppCompatActivity() {
    private lateinit var spinner1: Spinner
    private lateinit var spinner2: Spinner
    private lateinit var btn: Button
    private lateinit var t:TextView

    private val Sahibabad = 0
    private val Ghaziabad = 1
    private val Guldhar = 2
    private val Duhai = 3
    private val Duhai_Depot = 4
    private val MuradNagar = 5
    private val ModiNagar_North = 6
    private val ModiNagar_South = 7
    private val pathMatrix = arrayOf(
        arrayOf("", "Sahibabad -> Ghaziabad", "Sahibabad -> Ghaziabad -> Guldhar", "Sahibabad -> Ghaziabad -> Guldhar -> Duhai", "Sahibabad -> Ghaziabad -> Guldhar -> Duhai -> Duhai Depot", "Sahibabad -> Ghaziabad -> Guldhar -> Duhai -> Muradnagar", "Sahibabad -> Ghaziabad -> Guldhar -> Duhai -> Muradnagar -> Modinagar (North)", "Sahibabad -> Ghaziabad -> Guldhar -> Duhai -> Muradnagar -> Modinagar (North) -> Modinagar (South)"), // Sahibabad
        arrayOf("Ghaziabad -> Sahibabad", "", "Ghaziabad -> Guldhar", "Ghaziabad -> Guldhar -> Duhai", "Ghaziabad -> Guldhar -> Duhai -> Duhai Depot", "Ghaziabad -> Guldhar -> Duhai -> Muradnagar", "Ghaziabad -> Guldhar -> Duhai -> Muradnagar -> Modinagar (North)", "Ghaziabad -> Guldhar -> Duhai -> Muradnagar -> Modinagar (North) -> Modinagar (South)"), // Ghaziabad
        arrayOf("Guldhar -> Ghaziabad -> Sahibabad", "Guldhar -> Ghaziabad", "", "Guldhar -> Duhai", "Guldhar -> Duhai -> Duhai Depot", "Guldhar -> Duhai -> Muradnagar", "Guldhar -> Duhai -> Muradnagar -> Modinagar (North)", "Guldhar -> Duhai -> Muradnagar -> Modinagar (North) -> Modinagar (South)"), // Guldhar
        arrayOf("Duhai -> Guldhar -> Ghaziabad -> Sahibabad", "Duhai -> Guldhar -> Ghaziabad", "Duhai -> Guldhar", "", "Duhai -> Duhai Depot", "Duhai -> Muradnagar", "Duhai -> Muradnagar -> Modinagar (North)", "Duhai -> Muradnagar -> Modinagar (North) -> Modinagar (South)"), // Duhai
        arrayOf("Duhai Depot -> Duhai -> Guldhar -> Ghaziabad -> Sahibabad", "Duhai Depot -> Duhai -> Guldhar -> Ghaziabad", "Duhai Depot -> Duhai -> Guldhar", "Duhai Depot -> Duhai", "", "Duhai Depot -> Duhai -> Muradnagar", "Duhai Depot -> Duhai -> Muradnagar -> Modinagar (North)", "Duhai Depot -> Duhai -> Muradnagar -> Modinagar (North) -> Modinagar (South)"), // Duhai Depot
        arrayOf("Muradnagar -> Duhai -> Guldhar -> Ghaziabad -> Sahibabad", "Muradnagar -> Duhai -> Guldhar -> Ghaziabad", "Muradnagar -> Duhai -> Guldhar", "Muradnagar -> Duhai", "Muradnagar -> Duhai -> Duhai Depot", "", "Muradnagar -> Modinagar (North)", "Muradnagar -> Modinagar (North) -> Modinagar (South)"), // Muradnagar
        arrayOf("Modinagar (North) -> Muradnagar -> Duhai -> Guldhar -> Ghaziabad -> Sahibabad", "Modinagar (North) -> Muradnagar -> Duhai -> Guldhar -> Ghaziabad", "Modinagar (North) -> Muradnagar -> Duhai -> Guldhar", "Modinagar (North) -> Muradnagar -> Duhai", "Modinagar (North) -> Muradnagar -> Duhai -> Duhai Depot", "Modinagar (North) -> Muradnagar", "", "Modinagar (North) -> Modinagar (South)"), // Modinagar (North)
        arrayOf("Modinagar (South) -> Modinagar (North) -> Muradnagar -> Duhai -> Guldhar -> Ghaziabad -> Sahibabad", "Modinagar (South) -> Modinagar (North) -> Muradnagar -> Duhai -> Guldhar -> Ghaziabad", "Modinagar (South) -> Modinagar (North) -> Muradnagar -> Duhai -> Guldhar", "Modinagar (South) -> Modinagar (North) -> Muradnagar -> Duhai", "Modinagar (South) -> Modinagar (North) -> Muradnagar -> Duhai -> Duhai Depot", "Modinagar (South) -> Modinagar (North) -> Muradnagar", "Modinagar (South) -> Modinagar (North)", "") // Modinagar (South)
    )

    private val costMatrix = arrayOf(
        arrayOf(0, 30, 30, 40, 50, 60, 90, 80),
        arrayOf(30, 0, 20, 30, 30, 40, 80, 60),
        arrayOf(30, 20, 0, 20, 30, 30, 60, 50),
        arrayOf(40, 30, 20, 0, 20, 20, 50, 40),
        arrayOf(50, 30, 30, 20, 0, 30, 60, 40),
        arrayOf(60, 40, 30, 20, 30, 0, 30, 20),
        arrayOf(90, 80, 60, 50, 60, 30, 0, 20),
        arrayOf(80, 60, 50, 40, 40, 20, 20, 0)
    )

    private val distMatrix = arrayOf(
        arrayOf(0.0, 5.5, 9.3, 13.3, 15.4, 19.5, 30.3, 26.3),
        arrayOf(5.5, 0.0, 4.3, 8.3, 10.4, 14.4, 25.2, 21.2),
        arrayOf(9.3, 4.3, 0.0, 3.9, 6.1, 10.1, 20.9, 16.9),
        arrayOf(13.3, 8.3, 3.9, 0.0, 3.9, 6.1, 16.9, 12.9),
        arrayOf(15.4, 10.4, 6.1, 3.9, 0.0, 10.1, 20.9, 16.9),
        arrayOf(19.5, 14.4, 10.1, 6.1, 10.1, 0.0, 6.8, 3.9),
        arrayOf(30.3, 25.2, 20.9, 16.9, 20.9, 6.8, 0.0, 3.9),
        arrayOf(26.3, 21.2, 16.9, 12.9, 16.9, 3.9, 3.9, 0.0)
    )

    private val timeMatrix = arrayOf(
        arrayOf(0.0, 4.5, 8.5, 12.5, 17.4, 17.1, 24.53, 21.28),
        arrayOf(4.5, 0.0, 3.3, 7.3, 12.2, 11.5, 19.33, 16.8),
        arrayOf(8.5, 3.3, 0.0, 3.3, 8.2, 7.5, 15.33, 12.8),
        arrayOf(12.5, 7.3, 3.3, 0.0, 3.3, 3.5, 11.33, 8.8),
        arrayOf(17.4, 12.2, 8.2, 3.3, 0.0, 7.5, 15.33, 12.8),
        arrayOf(17.1, 11.5, 7.5, 3.5, 7.5, 0.0, 7.13, 3.48),
        arrayOf(24.53, 19.33, 15.33, 11.33, 15.33, 7.13, 0.0, 2.55),
        arrayOf(21.28, 16.8, 12.8, 8.8, 12.8, 3.48, 2.55, 0.0)
    )

    private val array = listOf(
        "Sahibabad",
        "Ghaziabad",
        "Guldhar",
        "Duhai",
        "Duhai_Depot",
        "MuradNagar",
        "ModiNagar_North",
        "ModiNagar_South"
    )

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pathfinder)

        spinner1 = findViewById(R.id.From)
        spinner2 = findViewById(R.id.To)
        btn = findViewById(R.id.button3)
        t=findViewById(R.id.textView9)
        val adapter = ArrayAdapter(this, R.layout.custom_spinner_item, array)
        adapter.setDropDownViewResource(R.layout.custom_spinner_item)
        spinner1.adapter = adapter
        spinner2.adapter = adapter

        btn.setOnClickListener {
            finder()
        }
    }

    private fun finder() {
        val fromStation = spinner1.selectedItem.toString()
        val toStation = spinner2.selectedItem.toString()


        val fromIndex = getIndex(fromStation)
        val toIndex = getIndex(toStation)

        if (fromIndex != -1 && toIndex != -1) {
            val path = pathMatrix[fromIndex][toIndex]
            val cost = costMatrix[fromIndex][toIndex]
            val dist=distMatrix[fromIndex][toIndex]
            val time=timeMatrix[fromIndex][toIndex]
            val result = "Path: $path\nFare: ₹$cost\nDistance: $dist km\nTime: $time min"
            t.text = result
            Log.d("PathFinder", "Path from $fromStation to $toStation: $path, Fare: ₹$cost")

        } else {
            Log.e("PathFinder", "Invalid station name selected")

        }
    }

    private fun getIndex(station: String): Int {
        return when (station) {
            "Sahibabad" -> Sahibabad
            "Ghaziabad" -> Ghaziabad
            "Guldhar" -> Guldhar
            "Duhai" -> Duhai
            "Duhai_Depot" -> Duhai_Depot
            "MuradNagar" -> MuradNagar
            "ModiNagar_North" -> ModiNagar_North
            "ModiNagar_South" -> ModiNagar_South
            else -> -1
        }
    }
}

