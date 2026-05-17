package com.mappeo.app.ui.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun OnboardingScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToRegister: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text("Onboarding Screen")
        Button(onClick = onNavigateToLogin) {
            Text("Go to Login")
        }
        Button(onClick = onNavigateToRegister) {
            Text("Go to Register")
        }
    }
}
