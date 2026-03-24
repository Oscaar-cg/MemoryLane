package com.example.memorylane

import android.app.Activity
import android.content.Intent
import android.os.Bundle
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

        capsules.add(Capsule("Paris Trip", "Open July 31 2030"))
        capsules.add(Capsule("Birthday Message", "Open July 1 2030"))

        adapter = CapsuleAdapter(capsules)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        addButton.setOnClickListener {
            val intent = Intent(this, CreateCapsuleActivity::class.java)
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            val title = data?.getStringExtra("title") ?: ""
            val message = data?.getStringExtra("message") ?: ""

            capsules.add(Capsule(title, message))

            adapter.notifyDataSetChanged()
        }
    }
}