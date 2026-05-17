package com.mappeo.app.presentation.welcome

data class WelcomeUiState(
    val isLoading: Boolean = false,
    val error: String?     = null
)

/** Destinos possíveis de navegação disparados pela tela de boas-vindas */
sealed class WelcomeNavigationEvent {
    data object NavigateToLogin    : WelcomeNavigationEvent()
    data object NavigateToRegister : WelcomeNavigationEvent()
}