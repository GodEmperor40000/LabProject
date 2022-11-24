package com.example.db

import androidx.room.*

@Dao
interface TodoDao {
    @get:Query("SELECT * FROM todos")
    val all: List<TodoEntity>

    @Query("SELECT * FROM todos WHERE id = :id")
    fun getById(id: Long): TodoEntity

    @Insert
    fun insert(todo: TodoEntity): Long

    @Update
    fun update(todo: TodoEntity)

    @Delete
    fun delete(todo: TodoEntity)

    fun insertSpecial(Name: String, surName: String, birthDay: String, phoneNumber: String): TodoEntity {
        val todoEntity = TodoEntity()
        todoEntity.name = Name
        todoEntity.surName = surName
        todoEntity.birthDay = birthDay
        todoEntity.phoneNumber = phoneNumber
        todoEntity.id = this.insert(todoEntity)
        return todoEntity
    }
}