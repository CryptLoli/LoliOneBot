package io.github.crypt_loli.loli_onebot.server.reverse

import io.github.crypt_loli.loli_onebot.module.WSSend
import io.github.crypt_loli.loli_onebot.server.OneBotServer
import io.github.crypt_loli.loli_onebot.server.OneBotConnection
import io.ktor.client.plugins.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.launch

class ReverseWSConnection(
    val server: OneBotServer,
    val wrapped: DefaultClientWebSocketSession
): WSSend {
    val api = OneBotConnection(server, this)

    val job = server.scope.launch {
        runCatching {
            for (frame in wrapped.incoming) {
                when (frame) {
                    is Frame.Text -> onTextReceived(frame.readText())
                    else -> {}
                }
            }
        }
            .onFailure { e -> disconnectReason = e }
    }

    var disconnectReason: Throwable? = null

    fun onTextReceived(text: String) {
        server.scope.launch { server.handler.handleRaw(api, text) }
    }

    override suspend fun send(text: String) {
        wrapped.send(text)
    }

    override suspend fun disconnect() {
        wrapped.close()
    }
}
