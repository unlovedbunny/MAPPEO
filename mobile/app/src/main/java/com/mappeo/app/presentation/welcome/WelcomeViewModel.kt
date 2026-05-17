package com.mappeo.app.presentation.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(WelcomeUiState())
    val uiState: StateFlow<WelcomeUiState> = _uiState.asStateFlow()

    /** Canal de eventos de navegação (one-shot, não reenviado após rotation) */
    private val _navigationEvent = Channel<WelcomeNavigationEvent>(Channel.BUFFERED)
    val navigationEvent: Flow<WelcomeNavigationEvent> = _navigationEvent.receiveAsFlow()

    fun onSignInClicked() {
        viewModelScope.launch {
            _navigationEvent.send(WelcomeNavigationEvent.NavigateToLogin)
        }
    }

    fun onCreateAccountClicked() {
        viewModelScope.launch {
            _navigationEvent.send(WelcomeNavigationEvent.NavigateToRegister)
        }
    }
}