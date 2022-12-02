package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.db.TodoDatabase

class redactActivity : AppCompatActivity() {
    private val todoDatabase by lazy {
        TodoDatabase.getDatabase(this).todoDao()
    }

    @SuppressLint("MissingInflatedId")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_redact)

        val id = intent.getLongExtra(InformationActivity.EXTRA_KEY, 1)
        val person = todoDatabase.getById(id)

        val textViewName = findViewById<TextView>(R.id.editTextTextPersonName4)
        val textViewSurname = findViewById<TextView>(R.id.editTextTextPersonName5)
        val textViewPhoneNumber = findViewById<TextView>(R.id.editTextPhone2)
        val textViewBirthday = findViewById<TextView>(R.id.editTextTextPersonName6)
        val buttonReturn = findViewById<Button>(R.id.button7)
        val buttonRedact = findViewById<Button>(R.id.button8)

        textViewName.text = person.name
        textViewSurname.text = person.surName
        textViewBirthday.text = person.birthDay
        textViewPhoneNumber.text = person.phoneNumber

        buttonReturn.setOnClickListener{
            finish()
        }
        buttonRedact.setOnClickListener{
            person.name = textViewName.text.toString()
            person.surName = textViewSurname.text.toString()
            person.birthDay = textViewBirthday.text.toString()
            person.phoneNumber = textViewPhoneNumber.text.toString()
            todoDatabase.update(person)
            finish()
        }
    }
}