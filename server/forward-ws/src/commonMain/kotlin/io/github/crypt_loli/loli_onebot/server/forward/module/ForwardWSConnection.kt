package io.github.crypt_loli.loli_onebot.server.forward.module

import io.github.crypt_loli.loli_onebot.module.WSSend
import io.github.crypt_loli.loli_onebot.server.OneBotConnection
import io.github.crypt_loli.loli_onebot.server.OneBotServer
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class ForwardWSConnection(
    val system: OneBotServer,
    val wrapped: DefaultWebSocketServerSession
): WSSend {
    override val isConnected
        get() = wrapped.isActive
    val api = OneBotConnection(system, this)

    fun onTextReceived(text: String) {
        system.scope.launch { system.handler.handleRaw(api, text) }
    }

    override suspend fun send(text: String) {
        wrapped.send(text)
    }

    override suspend fun disconnect() {
        wrapped.close()
    }
}
