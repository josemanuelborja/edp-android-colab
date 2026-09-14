package com.example.borja.domain
 
import com.example.borja.core.AppResult
 
interface ChatRepository {
    suspend fun getMessages(): AppResult<List<Message>>
    suspend fun sendMessage(sender: String, text: String): AppResult<Unit>
}