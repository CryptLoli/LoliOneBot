package io.github.crypt_loli.loli_onebot.client.module

import io.github.crypt_loli.loli_onebot.OneBotApi
import io.github.crypt_loli.loli_onebot.client.LoliOneBotClient
import io.github.crypt_loli.loli_onebot.module.WSSend
import io.ktor.client.plugins.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.launch

class ForwardWSClient(
    val system: LoliOneBotClient,
    val wrapped: DefaultClientWebSocketSession
): WSSend {

    val api = OneBotApi(this, system.handler)

    val job = system.scope.launch {
        runCatching {
            for (frame in wrapped.incoming) {
                when (frame) {
                    is Frame.Text -> onTextReceived(frame.readText())
                    else -> {}
                }
            }
        }
            .onFailure { e -> disconnectReason = e }

        onDisconnect()
    }
    var disconnectReason: Throwable? = null

    fun onTextReceived(text: String) {
        system.scope.launch { system.handler.handleRaw(api, text) }
    }

    fun onDisconnect() {
        system.requests.cancelAll()
    }

    override suspend fun send(text: String) {
        wrapped.send(text)
    }

    override suspend fun disconnect() {
        wrapped.close()
    }

}
