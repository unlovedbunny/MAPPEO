package com.mappeo.app.ui.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun RegisterScreen(
    onNavigateBack: () -> Unit,
    onNavigateToLogin: () -> Unit,
    onRegisterSuccess: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text("Register Screen")
        Button(onClick = onRegisterSuccess) {
            Text("Register")
        }
        Button(onClick = onNavigateToLogin) {
            Text("Go to Login")
        }
        Button(onClick = onNavigateBack) {
            Text("Back")
        }
    }
}
