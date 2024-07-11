package com.ryannm.tasky.presentation.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

object ListScreen {
    const val route = "list"

    @Composable
    fun screen(
        listVM: ListVM = viewModel(),
        openDetailWithID:(Int)->Unit
    ) {
        val tasks by listVM.tasks.collectAsStateWithLifecycle()

        Column (modifier = Modifier.fillMaxSize()) {
            Text("${tasks.size} TASKS",
                modifier = Modifier.padding(start = 8.dp, top = 4.dp),
                style = MaterialTheme.typography.headlineLarge.apply {  })
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(tasks) {
                    Task(task = it,
                        onTaskClick = {
                            openDetailWithID(it.id)
                        }) { checked ->
                        listVM.setChecked(it, checked)
                    }
                }
            }
        }

    }
}
