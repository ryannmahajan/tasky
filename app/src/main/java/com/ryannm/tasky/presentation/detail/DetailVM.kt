package com.ryannm.tasky.presentation.detail

import androidx.lifecycle.ViewModel
import com.ryannm.tasky.domain.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailVM:ViewModel() {
    private val _task = MutableStateFlow(Task())
    val task = _task.asStateFlow()

    fun onTitleChange(it:String) {
        _task.value = _task.value.copy(title = it)
    }

    fun onDescriptionChange(it:String) {
        _task.value = _task.value.copy(description = it)
    }
}