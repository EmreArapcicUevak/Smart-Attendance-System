package com.example.smartattendancesystemandroid.ui.screens.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smartattendancesystemandroid.auth.AuthResult
import com.example.smartattendancesystemandroid.ui.theme.SmartAttendanceSystemAndroidTheme

@Composable
fun LoginScreen(
    navigateToStaffHomePage: () -> Unit,
    loginViewModel: LoginScreenViewModel = hiltViewModel()
) {


    val context = LocalContext.current
    LaunchedEffect(loginViewModel, context) {
        loginViewModel.authResult.collect { result ->
            when (result) {
                is AuthResult.Authorized -> {
                    navigateToStaffHomePage()
                }

                is AuthResult.Unauthorized -> {
                    Toast.makeText(context, "Not Authorized", Toast.LENGTH_LONG).show()
                }

                is AuthResult.UnknownError -> {
                    Toast.makeText(context, "Unknown error occurred", Toast.LENGTH_LONG).show()
                }
            }
        }
    }


    val loginUiState by loginViewModel.uiState.collectAsState()

    LoginScreenContent(
        loginUiState = loginUiState,
        onValueChangeEmailField = { it ->
            loginViewModel.onEmailFieldValueChange(it)
        },
        onValueChangePasswordField = { it ->
            loginViewModel.onPasswordFieldValueChange(it)
        },
        loginBtnPressed = { loginViewModel.loginBtnPressed() },
        openDialog = { loginViewModel.openDialog() },
        closeDialog = { loginViewModel.openDialog(false) }
    )
}

@Composable
private fun LoginScreenContent(
    loginUiState: LoginUiState,
    onValueChangeEmailField: (String) -> Unit,
    onValueChangePasswordField: (String) -> Unit,
    loginBtnPressed: () -> Unit,
    openDialog: () -> Unit,
    closeDialog: () -> Unit,
) {
    Scaffold { innerPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(innerPadding)
                .padding(8.dp)
                .fillMaxSize()
        ) {

            Text(
                text = "Login",
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(4.dp)
            )
            
            OutlinedTextField(
                value = loginUiState.emailFieldValue,
                onValueChange = onValueChangeEmailField,
                label = { Text(text = "Email") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                maxLines = 1,
                isError = loginUiState.isInvalidEmail,
                supportingText = {
                    if (loginUiState.isInvalidEmail) {
                        Text(text = "Enter a valid email")
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(4.dp)
            )
            OutlinedTextField(
                value = loginUiState.passwordFieldValue,
                onValueChange = onValueChangePasswordField,
                label = { Text(text = "Password") },
                visualTransformation = PasswordVisualTransformation(),
                maxLines = 1,
                modifier = Modifier.fillMaxWidth().padding(4.dp)
            )

            Text(
                text = "Forgot password",
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(4.dp).clickable(onClick = openDialog)
            )

            Button(
                onClick = {
                    loginBtnPressed()
                },
                enabled = loginUiState.emailFieldValue.isNotEmpty() && loginUiState.passwordFieldValue.isNotEmpty(),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth().padding(4.dp)
            ) {
                Text(
                    text = "Login",
                    style = MaterialTheme.typography.titleMedium
                )
            }

            if (loginUiState.dialogOpen) {
                ForgotPasswordDialog(closeDialog)
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordDialog(closeDialog: () -> Unit) {
    BasicAlertDialog(onDismissRequest = closeDialog) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(horizontal = 10.dp, vertical = 20.dp)
        ) {
            Text(
                text = "Contact your administrator for help",
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
fun LoginScreenPreview() {
    SmartAttendanceSystemAndroidTheme {
        LoginScreenContent(
            loginUiState = LoginUiState(),
            onValueChangeEmailField = {},
            onValueChangePasswordField = {},
            loginBtnPressed = {},
            openDialog = {},
            closeDialog = {}
        )
    }
}

@Preview
@Composable
fun ForgotPasswordDialogPreview() {
    SmartAttendanceSystemAndroidTheme {
        ForgotPasswordDialog(closeDialog = {})
    }
}
