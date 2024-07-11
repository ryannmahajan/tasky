@file:OptIn(ExperimentalMaterial3Api::class)

package com.ryannm.tasky.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ryannm.tasky.R

@Composable
fun DetailScreen(
    detailVM: DetailVM = viewModel()
) {
    val task by detailVM.task.collectAsStateWithLifecycle()
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        OutlinedTextField(value = task.title,
            onValueChange = detailVM::onTitleChange,
            textStyle = MaterialTheme.typography.titleLarge,
            label = {Text(stringResource(R.string.title))})
        TextField(value = task.description,
            onValueChange = detailVM::onDescriptionChange,
            textStyle = MaterialTheme.typography.bodyLarge,
            label = {Text(stringResource(R.string.description))})
        Button(onClick = {} ) {
            Text("Save")
        }
    }
}

@Composable
@Preview
private fun detailScreenPreview() = DetailScreen()