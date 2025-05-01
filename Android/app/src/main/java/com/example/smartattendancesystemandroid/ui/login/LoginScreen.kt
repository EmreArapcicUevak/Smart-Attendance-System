package com.example.smartattendancesystemandroid.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.smartattendancesystemandroid.ui.theme.SmartAttendanceSystemAndroidTheme

@Composable
fun LoginScreen(
    loginViewModel: LoginScreenViewModel = viewModel<LoginScreenViewModel>()
) {

    val loginUiState by loginViewModel.uiState.collectAsState()

    Scaffold { innerPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(innerPadding).padding(8.dp)
                .fillMaxSize()
        ) {
            OutlinedTextField(
                value = loginUiState.emailFieldValue,
                onValueChange = {it -> loginViewModel.onEmailFieldValueChange(it)},
                label = { Text(text = "Email") },
                maxLines = 1,
            )
            OutlinedTextField(
                value = loginUiState.passwordFieldValue,
                onValueChange = {it -> loginViewModel.onPasswordFieldValueChange(it)},
                label = { Text(text = "Password") },
                visualTransformation = PasswordVisualTransformation(),
                maxLines = 1,
            )
            ClickableText(text = AnnotatedString(text = "Forgot password", spanStyle = SpanStyle(color = MaterialTheme.colorScheme.onSurfaceVariant)),
                onClick = {
                    loginViewModel.openDialog()
                }
            )

            Button(onClick = {
                loginViewModel.login()
            }) {
                Text(text = "Login")
            }

            if(loginUiState.dialogOpen) {
                ForgotPasswordDialog(loginViewModel)
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordDialog(loginViewModel: LoginScreenViewModel) {
    BasicAlertDialog(onDismissRequest = {loginViewModel.openDialog(false)}) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(horizontal = 10.dp, vertical = 20.dp)
        ) {
            Text(text = "Contact your administrator for help",
                modifier = Modifier.padding(bottom = 30.dp))
            Button(onClick = {loginViewModel.openDialog(false)}) {
                Text(text = "Ok")
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    SmartAttendanceSystemAndroidTheme {
        LoginScreen()
    }
}
