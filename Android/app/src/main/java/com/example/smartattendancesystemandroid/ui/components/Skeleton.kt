package com.example.smartattendancesystemandroid.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Skeleton(
    topAppBarTitle: String,
    settingsPressed: () -> Unit,
    logoutPressed: () -> Unit,
    canNavigateBack: Boolean = false,
    navigateBackPressed: () -> Unit,
    additionalActions: @Composable () -> Unit = {},
    content: @Composable () -> Unit,
) {

    var showDropdown by remember { mutableStateOf(false) }


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = topAppBarTitle)
                },
                navigationIcon = {
                    if (canNavigateBack) {
                        IconButton(onClick = navigateBackPressed) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Go back"
                            )
                        }
                    }
                },
                actions = {
                    Row {
                        additionalActions()
                        Box {
                            IconButton(
                                onClick = {
                                    showDropdown = !showDropdown
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.AccountCircle,
                                    contentDescription = null,
                                    modifier = Modifier.size(35.dp)
                                )
                            }
                            DropdownMenu(
                                expanded = showDropdown,
                                onDismissRequest = { showDropdown = false },
                            ) {
                                DropdownMenuItem(
                                    text = { Text(text = "Settings", modifier = Modifier.padding(end = 8.dp)) },
                                    leadingIcon = { Icon(Icons.Outlined.Settings, contentDescription = null) },
                                    onClick = settingsPressed
                                )
                                DropdownMenuItem(
                                    text = { Text(text = "Logout", modifier = Modifier.padding(end = 8.dp)) },
                                    leadingIcon = {Icon(Icons.AutoMirrored.Outlined.ExitToApp, contentDescription = null)},
                                    onClick = logoutPressed
                                )
                            }
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(8.dp)
                .fillMaxSize()
        ) {
            content()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SkeletonPreview() {
    Skeleton(
        topAppBarTitle = "Hello World",
        settingsPressed = {},
        logoutPressed = {},
        navigateBackPressed = {}
    ) {
        Text(text = "test")
    }
}