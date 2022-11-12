package com.example.db

import androidx.room.*

@Entity(tableName = "todos")
class TodoEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
    var title: String = ""
    var isDone = false
}