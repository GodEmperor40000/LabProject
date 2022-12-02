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
    private val arr = mutableListOf<TodoEntity>()
    private val list = mutableListOf<TodoEntity>()
    private lateinit var adapter: RecyclerAdapter
    private lateinit var searchText: SearchView
    companion object {
        const val EXTRA_KEY = "EXTRA"
    }
    lateinit var db: TodoDatabase
    lateinit var todoDao: TodoDao
    private val todoDatabase by lazy {
        TodoDatabase.getDatabase(this).todoDao()
    }
    val REQUEST_CODE = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        arr.addAll(selectAll())
        val recView = findViewById<RecyclerView>(R.id.recyclerView)
        val button = findViewById<Button>(R.id.button)
        searchText = findViewById<SearchView>(R.id.searchText)
        adapter = RecyclerAdapter(arr) {
            val intent1 = Intent(this, InformationActivity::class.java)
            intent1.putExtra(EXTRA_KEY, arr[it].id)
            startActivityForResult(intent1, REQUEST_CODE)
        }

        recView.layoutManager = LinearLayoutManager(this)
        recView.adapter = adapter

        button.setOnClickListener {
            val intent = Intent(this, BrandNewActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }

        searchText.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filter(newText.toString())
                return false
            }


        })
    }
    fun filter(text: String){
        if(text != ""){
            arr.clear()
            list.addAll(selectAll())
            arr.add(todoDatabase.all.filter{(it.name+" "+it.surName).contains(text, ignoreCase = true)}[0])
            adapter.notifyDataSetChanged()
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        println("onActivityResult")

        arr.clear()
        arr.addAll(selectAll())

        adapter.notifyDataSetChanged()

    }


    fun insert(Name: String, surName: String, birthDay: String, phoneNumber: String): TodoEntity {
        val todoEntity = TodoEntity()
        todoEntity.name = Name
        todoEntity.surName = surName
        todoEntity.birthDay = birthDay
        todoEntity.phoneNumber = phoneNumber
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
        entity.name = "true";
        todoDao.update(entity);
    }

    fun delete(entity:TodoEntity){
        todoDao.delete(entity);
    }

}