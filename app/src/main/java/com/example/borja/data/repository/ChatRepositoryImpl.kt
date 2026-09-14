package com.example.borja.data.repository
 
import com.example.borja.core.AppResult
import com.example.borja.data.local.MessageDao
import com.example.borja.data.network.ChatApiService
import com.example.borja.data.network.dto.NewMessageDto
import com.example.borja.data.network.dto.toDomain
import com.example.borja.data.network.dto.toEntity
import com.example.borja.domain.ChatRepository
import com.example.borja.domain.Message
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
 
class ChatRepositoryImpl(
    private val api: ChatApiService,
    private val dao: MessageDao
) : ChatRepository {
 
    override suspend fun getMessages(): AppResult<List<Message>> {
        val result = safeCall { api.getMessages().toDomain() }
 
        if (result is AppResult.Success) {
            dao.insertAll(result.data.map { it.toEntity() })
            return result
        }
 
        val saved = dao.getAll().map { it.toDomain() }
        return if (saved.isNotEmpty()) AppResult.Success(saved) else result
    }
 
    override suspend fun sendMessage(sender: String, text: String): AppResult<Unit> =
        safeCall {
            val dto = NewMessageDto(sender, text, System.currentTimeMillis())
            api.sendMessage(dto)
            Unit
        }
 
    private inline fun <T> safeCall(block: () -> T): AppResult<T> =
        try { AppResult.Success(block()) }
        catch (e: UnknownHostException)   { AppResult.Failure.NoInternet }
        catch (e: SocketTimeoutException) { AppResult.Failure.Timeout }
        catch (e: IOException)            { AppResult.Failure.NoInternet }
        catch (e: Exception)              { AppResult.Failure.Unknown(e.message) }
}