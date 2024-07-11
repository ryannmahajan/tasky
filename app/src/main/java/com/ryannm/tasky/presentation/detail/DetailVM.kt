package com.ryannm.tasky.presentation.detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryannm.tasky.data.TaskDatabase
import com.ryannm.tasky.domain.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val TAG = "DetailVM"
class DetailVM(
    private val savedStateHandle: SavedStateHandle
):ViewModel() {
    private val _task = MutableStateFlow(Task())
    val task = _task.asStateFlow()

    private val _goBack = MutableStateFlow(false)
    val goBack = _goBack.asStateFlow()

    init {
        viewModelScope.launch {
            (savedStateHandle.get(DetailScreen.taskId) as Int?)?.let {
                if (it!=0) {
                    _task.value = TaskDatabase.getDao().getTaskById(it)
                    Log.d(TAG, "Got task ${task.value}")
                }
            }
        }
    }

    fun onTitleChange(it:String) {
        _task.value = _task.value.copy(title = it)
    }

    fun onDescriptionChange(it:String) {
        _task.value = _task.value.copy(description = it)
    }

    fun onSave() {
        val id = ((savedStateHandle.get(DetailScreen.taskId) as Int?))
        viewModelScope.launch {
            if (id==null || id==0) {
                TaskDatabase.getDao().insert(task.value)
            } else {
                TaskDatabase.getDao().update(task.value)
            }
            Log.d(TAG, "Saved ${task.value}")
            _goBack.value = true
        }
    }
}