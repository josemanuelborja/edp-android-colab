package com.example.borja.ui
 
import com.example.borja.domain.Message
 
sealed interface ChatUiState {
    data object Loading : ChatUiState
    data object Empty   : ChatUiState
    data class  Ready(val messages: List<Message>) : ChatUiState
    data class  Error(val message: String) : ChatUiState
}