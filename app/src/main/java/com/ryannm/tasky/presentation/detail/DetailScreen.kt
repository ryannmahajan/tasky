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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.ryannm.tasky.R

object DetailScreen {
    private const val route = "detail"
    const val taskId = "taskID"

    const val routeWithArguments = "$route?$taskId={$taskId}"
    val arguments= listOf(
        navArgument(taskId) {
            type = NavType.IntType
            defaultValue = 0
        }
    )

    fun route(id:Int?):String = "$route?$taskId=$id"
    @Composable
    fun screen(
        detailVM: DetailVM = viewModel(),
        goToListScreen:()->Unit
    ) {
        val task by detailVM.task.collectAsStateWithLifecycle()
        val goBack by detailVM.goBack.collectAsStateWithLifecycle()

        LaunchedEffect(goBack) {
            if (goBack) goToListScreen()
        }

        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            OutlinedTextField(value = task.title,
                onValueChange = detailVM::onTitleChange,
                textStyle = MaterialTheme.typography.titleLarge,
                label = {Text(stringResource(R.string.title))})
            TextField(value = task.description,
                onValueChange = detailVM::onDescriptionChange,
                textStyle = MaterialTheme.typography.bodyLarge,
                label = {Text(stringResource(R.string.description))})
            Button(onClick = detailVM::onSave ) {
                Text("Save")
            }
        }
    }

}

@Composable
@Preview
private fun detailScreenPreview() = DetailScreen.screen {}