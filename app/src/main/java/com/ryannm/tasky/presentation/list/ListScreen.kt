@file:OptIn(ExperimentalMaterial3Api::class)

package com.ryannm.tasky.presentation.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ryannm.tasky.R

object ListScreen {
    const val route = "list"

    @Composable
    fun screen(
        listVM: ListVM = viewModel(),
        openDetailWithID:(Int)->Unit
    ) {
        val tasks by listVM.tasks.collectAsStateWithLifecycle()

        Scaffold (
            topBar = {
                Column(modifier = Modifier.fillMaxWidth()) {
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
//                    Text("${tasks.size} TASKS",
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .background(MaterialTheme.colorScheme.primary)
//                            .padding(start = 28.dp, top = 4.dp, bottom = 2.dp),
//                        color = MaterialTheme.colorScheme.onPrimary,
//                        style = MaterialTheme.typography.titleLarge)
                }
            },
            floatingActionButton = {
                FloatingActionButton(onClick = { openDetailWithID(0) }) {
                    Icon(painterResource(R.drawable.baseline_add_24), stringResource(R.string.add_button))
                }
            },
            floatingActionButtonPosition = FabPosition.End
        ) {
            Column (modifier = Modifier
                .padding(it)
                .fillMaxSize()) {
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
}

@Preview
@Composable
private fun listPreview() {
    ListScreen.screen(openDetailWithID = {})
}