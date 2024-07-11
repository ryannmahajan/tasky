package com.ryannm.tasky.presentation.list

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryannm.tasky.data.TaskDatabase
import com.ryannm.tasky.domain.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val TAG = "ListVM"
class ListVM:ViewModel() {
    private val _tasks = MutableStateFlow(listOf<Task>())
    val tasks = _tasks.asStateFlow()

    init {
        getTasks()
    }

    fun getTasks() {
        viewModelScope.launch {
            Log.d(TAG, "Getting tasks")
            _tasks.value = TaskDatabase.getDao().getAll()
        }
    }

    fun setChecked(task: Task, checked: Boolean) {
        viewModelScope.launch {
            Log.d(TAG, "Updating task")
            TaskDatabase.getDao().update(task.copy(completed = checked))
            getTasks()
        }
    }
}