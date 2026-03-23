package com.example.memorylane

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.content.Intent
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val addButton = findViewById<FloatingActionButton>(R.id.addButton)

        addButton.setOnClickListener {
            val intent = Intent(this, CreateCapsuleActivity::class.java)
            startActivity(intent)
        }

        val capsules = listOf(
            Capsule("Paris Trip", "Open July 31 2030"),
            Capsule("Birthday Message", "Open July 1 2030"),
            Capsule("New Year", "Open Jan 1 2050")
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CapsuleAdapter(capsules)
    }
}