package com.example.memorylane

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import android.app.DatePickerDialog
import java.util.Calendar

class CreateCapsuleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_capsule)

        val titleInput = findViewById<EditText>(R.id.titleInput)
        val messageInput = findViewById<EditText>(R.id.messageInput)
        val saveButton = findViewById<Button>(R.id.saveButton)
        val dateButton = findViewById<Button>(R.id.dateButton)

        var selectedDate: Long = System.currentTimeMillis()

        //DATE PICKER
        dateButton.setOnClickListener {
            val calendar = Calendar.getInstance()

            DatePickerDialog(this,
                { _, year, month, day ->
                    val cal = Calendar.getInstance()
                    cal.set(year, month, day)
                    selectedDate = cal.timeInMillis

                    dateButton.text = "$day/${month + 1}/$year"
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        // SAVE
        saveButton.setOnClickListener {

            val title = titleInput.text.toString()
            val message = messageInput.text.toString()

            val intent = Intent()
            intent.putExtra("title", title)
            intent.putExtra("message", message)

            // 🔥 AJOUT IMPORTANT (LA DATE)
            intent.putExtra("date", selectedDate)

            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}