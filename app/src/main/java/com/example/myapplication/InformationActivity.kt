package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.db.TodoDatabase

class InformationActivity : AppCompatActivity() {

    private val todoDatabase by lazy {
        TodoDatabase.getDatabase(this).todoDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)

        val id = intent.getLongExtra(MainActivity.EXTRA_KEY, 1)
        val person = todoDatabase.getById(id)

        val buttonReturn = findViewById<Button>(R.id.button3)
        val buttonRedact = findViewById<Button>(R.id.button5)
        val buttonDelete = findViewById<Button>(R.id.button6)
        val textViewName = findViewById<TextView>(R.id.textView6)
        val textViewSurname = findViewById<TextView>(R.id.textView7)
        val textViewPhoneNumber = findViewById<TextView>(R.id.textView8)
        val textViewBirthday = findViewById<TextView>(R.id.textView4)
        textViewName.text = person.name
        textViewSurname.text = person.surName
        textViewBirthday.text = person.birthDay
        textViewPhoneNumber.text = person.phoneNumber


        buttonReturn.setOnClickListener{
            finish()
        }
        buttonDelete.setOnClickListener{
            todoDatabase.delete(person)
            finish()
        }
        buttonRedact.setOnClickListener {

        }
    }
}