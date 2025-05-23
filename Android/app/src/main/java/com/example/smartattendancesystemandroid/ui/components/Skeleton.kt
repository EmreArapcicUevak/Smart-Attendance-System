package com.example.smartattendancesystemandroid.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smartattendancesystemandroid.ui.theme.SmartAttendanceSystemAndroidTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Skeleton(
    topAppBarTitle: String,
    logoutPressed: () -> Unit,
    canNavigateBack: Boolean = false,
    navigateBackPressed: () -> Unit,
    additionalActions: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    content: @Composable () -> Unit,
) {

    var showDropdown by remember { mutableStateOf(false) }
    var showContactAdminDialog by remember {mutableStateOf(false)}


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
                                    imageVector = Icons.Filled.Menu,
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
                                    onClick = {showContactAdminDialog = true}
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
        },
        floatingActionButton = floatingActionButton,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(top = 8.dp, start = 8.dp, end = 8.dp)
                .fillMaxSize()
        ) {
            content()
            if (showContactAdminDialog) {
                ContactAdminDialog(closeDialog = {showContactAdminDialog = false})
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ContactAdminDialog(closeDialog: () -> Unit = {}) {
    BasicAlertDialog(onDismissRequest = closeDialog) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(horizontal = 20.dp, vertical = 20.dp)
        ) {
            Text(
                text = "Contact your administrator to change your settings.",
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 30.dp)
            )
            Button(
                onClick = closeDialog,
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "Ok")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SkeletonPreview() {
    SmartAttendanceSystemAndroidTheme {
        Skeleton(
            topAppBarTitle = "Hello World",
            logoutPressed = {},
            navigateBackPressed = {}
        ) {
            Text(text = "test")
        }
    }
}

@Preview
@Composable
private fun ContactAdminDialogPreview() {
    SmartAttendanceSystemAndroidTheme {
        ContactAdminDialog()
    }
}