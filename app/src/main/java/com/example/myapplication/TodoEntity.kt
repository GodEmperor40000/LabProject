package com.example.db

import androidx.room.*
import java.util.*

@Entity(tableName = "todos")
class TodoEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
    var name: String = ""
    var surName: String = ""
    var birthDay: String = ""
    var phoneNumber: String = ""
}