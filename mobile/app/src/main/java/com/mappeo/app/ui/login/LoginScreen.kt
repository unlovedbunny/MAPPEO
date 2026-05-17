package com.mappeo.app.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LoginScreen(
    onNavigateBack: () -> Unit,
    onNavigateToRegister: () -> Unit,
    onLoginSuccess: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text("Login Screen")
        Button(onClick = onLoginSuccess) {
            Text("Login")
        }
        Button(onClick = onNavigateToRegister) {
            Text("Go to Register")
        }
        Button(onClick = onNavigateBack) {
            Text("Back")
        }
    }
}
