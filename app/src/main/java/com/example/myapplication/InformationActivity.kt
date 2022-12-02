package com.example.myapplication

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.db.TodoDatabase

class InformationActivity : AppCompatActivity() {
    private lateinit var adapter: RecyclerAdapter
    private val todoDatabase by lazy {
        TodoDatabase.getDatabase(this).todoDao()
    }

    companion object {
        const val EXTRA_KEY = "EXTRA"
    }

    fun dialogYesOrNo(
        activity: InformationActivity,
        title: String,
        message: String,
        listener: DialogInterface.OnClickListener
    ) {
        val builder = AlertDialog.Builder(activity)
        builder.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, id ->
            dialog.dismiss()
            listener.onClick(dialog, id)
        })
        builder.setNegativeButton("No", null)
        val alert = builder.create()
        alert.setTitle(title)
        alert.setMessage(message)
        alert.show()
    }

/* пример использования
    dialogYesOrNo(
    this,
    "Вопрос",
    "Вы перестали пить коньяк по утрам?",
    DialogInterface.OnClickListener { dialog, id ->
        // что делать, если нажали "да"
    }
})
*/
    val REQUEST_CODE = 1
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
            dialogYesOrNo(
                this,
                "Delete",
                "Do you wanna delete this contact",
                DialogInterface.OnClickListener{ dialog, id ->
                    todoDatabase.delete(person)
                    finish()
                }
            )

        }
        buttonRedact.setOnClickListener {
            val intent1 = Intent(this, redactActivity::class.java)
            intent1.putExtra(MainActivity.EXTRA_KEY, id)
            startActivityForResult(intent1, REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        println("onActivityResult")
        finish()
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

        //val person = todoDatabase.getById(id)


    }
}