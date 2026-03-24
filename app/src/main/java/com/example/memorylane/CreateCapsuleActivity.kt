package com.example.memorylane

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class CreateCapsuleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_capsule)

        val titleInput = findViewById<EditText>(R.id.titleInput)
        val messageInput = findViewById<EditText>(R.id.messageInput)
        val saveButton = findViewById<Button>(R.id.saveButton)

        saveButton.setOnClickListener {

            val title = titleInput.text.toString()
            val message = messageInput.text.toString()

            val intent = Intent()
            intent.putExtra("title", title)
            intent.putExtra("message", message)

            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}