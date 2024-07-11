package com.ryannm.tasky.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ryannm.tasky.domain.TaskDao
import com.ryannm.tasky.domain.model.Task

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    companion object {
        private lateinit var instance:TaskDatabase

        fun init(applicationContext: Context) {
            instance = Room.databaseBuilder(
                applicationContext,
                TaskDatabase::class.java, "task-db"
            ).build()
        }

        fun getDao() = instance.taskDao()
    }
}
