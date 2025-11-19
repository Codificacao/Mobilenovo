import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.BuildConfig
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// Modelo de dados para a mensagem
data class Message(val text: String, val isUser: Boolean)

class ChatViewModel : ViewModel() {

    // AQUI ESTÁ A MÁGICA: Usando a chave que você configurou no properties
    // Certifique-se de importar o BuildConfig do seu pacote (ex: com.example.nossotcc.BuildConfig)
    private val apiKey = BuildConfig.API_KEY

    private val generativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = apiKey
    )

    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages: StateFlow<List<Message>> = _messages.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun sendMessage(userMessage: String) {
        _messages.value += Message(userMessage, isUser = true)
        _isLoading.value = true

        viewModelScope.launch {
            try {
                val response = generativeModel.generateContent(userMessage)

                response.text?.let { modelResponse ->
                    _messages.value += Message(modelResponse, isUser = false)
                }
            } catch (e: Exception) {
                _messages.value += Message("Erro: ${e.localizedMessage}", isUser = false)
            } finally {
                _isLoading.value = false
            }
        }
    }
}