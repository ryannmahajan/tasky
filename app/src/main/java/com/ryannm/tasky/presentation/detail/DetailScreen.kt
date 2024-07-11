@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.ryannm.tasky.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

        Scaffold (
            topBar = {
                    TopAppBar(title = {
                        Text(
                            stringResource(R.string.app_name),
                            modifier = Modifier.padding(start = 8.dp, top = 8.dp),
                            style = MaterialTheme.typography.headlineLarge.copy(
                            ))

                    }, colors =  TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary
                    ))
            },
            floatingActionButton = {
                FloatingActionButton(onClick = detailVM::onSave ) {
                    Icon(painterResource(R.drawable.baseline_save_24), stringResource(R.string.save_button))
                }
            },
            floatingActionButtonPosition = FabPosition.End
        ) { padding ->
            Column (
                modifier = Modifier.padding(padding).fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                OutlinedTextField(value = task.title,
                    onValueChange = detailVM::onTitleChange,
                    textStyle = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    label = {Text(stringResource(R.string.title))})
                OutlinedTextField(value = task.description,
                    onValueChange = detailVM::onDescriptionChange,
                    textStyle = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.fillMaxWidth().padding(16.dp).fillMaxHeight(0.3f),
                    label = {Text(stringResource(R.string.description))})
            }
        }
    }

}

@Composable
@Preview
private fun detailScreenPreview() = DetailScreen.screen {}