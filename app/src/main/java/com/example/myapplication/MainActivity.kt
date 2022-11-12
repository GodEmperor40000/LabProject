package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.db.TodoDao
import com.example.db.TodoDatabase
import com.example.db.TodoEntity

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: RecyclerAdapter

    companion object {
        const val EXTRA_KEY = "EXTRA"
    }
    lateinit var db: TodoDatabase
    lateinit var todoDao: TodoDao
    private val todoDatabase by lazy {
        TodoDatabase.getDatabase(this).todoDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val arr = mutableListOf<TodoEntity>()
        adapter = RecyclerAdapter(arr) {
            val intent = Intent(this, BrandNewActivity::class.java)
            intent.putExtra(EXTRA_KEY, arr[it].id)
            startActivity(intent)
        }

        val editText = findViewById<EditText>(R.id.editTextNumber)
        val recView = findViewById<RecyclerView>(R.id.recyclerView)
        val button = findViewById<Button>(R.id.button)

        recView.layoutManager = LinearLayoutManager(this)
        recView.adapter = adapter

            button.setOnClickListener{
                val n = editText.text.toString()
                if (n.trim().isNotEmpty()) {
                    arr.add(insert(n))
                }
                adapter.notifyItemInserted(arr.lastIndex)
            }
    }

    fun insert(Name: String): TodoEntity {
        val todoEntity = TodoEntity()
        todoEntity.title = Name
        todoEntity.id = todoDao.insert(todoEntity)
        return todoEntity
    }

    fun selectAll(): List<TodoEntity> {
        return todoDatabase.all
    }

    fun selectById(Id:Int): TodoEntity {
        return todoDao.getById(1)
    }

    fun updateSelectedEntity(entity:TodoEntity){
        entity.isDone = true;
        todoDao.update(entity);
    }

    fun delete(entity:TodoEntity){
        todoDao.delete(entity);
    }

}