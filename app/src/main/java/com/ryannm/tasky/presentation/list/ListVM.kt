package com.ryannm.tasky.presentation.list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ryannm.tasky.domain.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ListVM:ViewModel() {
    private val _tasks = MutableStateFlow(listOf<Task>())
    val tasks = _tasks.asStateFlow()

    init {
        _tasks.value = listOf(
            Task(title = "Task 1"),
            Task(completed = true, title = "Task 2")

        )
    }
}