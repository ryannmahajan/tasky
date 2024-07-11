package com.ryannm.tasky.domain

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ryannm.tasky.domain.model.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    suspend fun getAll(): List<Task>

    @Query("SELECT * FROM task WHERE id=:id")
    suspend fun getTaskById(id:Int): Task

    @Update
    suspend fun update(task: Task)

    @Insert
    suspend fun insert(task: Task)

    @Delete
    suspend fun delete(task: Task)
}
