package com.example.memorylane

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val capsules = mutableListOf<Capsule>()
    private lateinit var adapter: CapsuleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val addButton = findViewById<FloatingActionButton>(R.id.addButton)

        //Capsules initiales (avec date future)
        capsules.add(Capsule("Paris Trip", "Amazing trip", System.currentTimeMillis() + 100000000))
        capsules.add(Capsule("Birthday", "My birthday", System.currentTimeMillis() + 200000000))

        adapter = CapsuleAdapter(capsules) { capsule ->

            val detailContainer = findViewById<FrameLayout?>(R.id.detailContainer)

            if (detailContainer != null) {
                val textView = TextView(this)
                textView.text = "Title: ${capsule.title}\n\nMessage: ${capsule.message}"
                textView.textSize = 18f

                detailContainer.removeAllViews()
                detailContainer.addView(textView)
            }
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        addButton?.setOnClickListener {
            val intent = Intent(this, CreateCapsuleActivity::class.java)
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            val title = data?.getStringExtra("title") ?: ""
            val message = data?.getStringExtra("message") ?: ""
            val date = data?.getLongExtra("date", System.currentTimeMillis())
                ?: System.currentTimeMillis()

            capsules.add(Capsule(title, message, date))
            adapter.notifyDataSetChanged()
        }
    }
}