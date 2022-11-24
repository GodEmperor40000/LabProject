package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.db.TodoDatabase

class BrandNewActivity : AppCompatActivity() {

    private val todoDatabase by lazy {
        TodoDatabase.getDatabase(this).todoDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brand_new)



        val s = intent.getIntExtra(MainActivity.EXTRA_KEY, 0)
        val textViewName = findViewById<EditText>(R.id.editTextTextPersonName)
        val textViewSurname = findViewById<EditText>(R.id.editTextTextPersonName2)
        val textViewPhoneNumber = findViewById<EditText>(R.id.editTextPhone)
        val textViewBirthday = findViewById<EditText>(R.id.editTextTextPersonName3)
        val buttonReturn = findViewById<Button>(R.id.button2)
        val buttonCreate = findViewById<Button>(R.id.button4)
        buttonReturn.setOnClickListener{
            finish()
        }
        buttonCreate.setOnClickListener{
            val name = textViewName.text.toString()
            val surName = textViewSurname.text.toString()
            val birthDay = textViewBirthday.text.toString()
            val phoneNumber = textViewPhoneNumber.text.toString()
            todoDatabase.insertSpecial(name, surName, birthDay, phoneNumber)
            finish()
        }

    }
}