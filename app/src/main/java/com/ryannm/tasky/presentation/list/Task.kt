package com.ryannm.tasky.presentation.list

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import com.ryannm.tasky.domain.model.Task

@Composable
fun Task(modifier: Modifier = Modifier, task: Task, onCheckedChange: ((Boolean) -> Unit) = {}) {
    Row (
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(checked = task.completed, onCheckedChange = onCheckedChange)
        Text(task.title, style = MaterialTheme.typography.titleLarge.copy(
            textDecoration = if (task.completed) TextDecoration.LineThrough else TextDecoration.None
        ))
    }
}