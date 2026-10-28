package io.github.crypt_loli.loli_onebot.server.module

import io.github.crypt_loli.loli_onebot.OneBotApi
import io.github.crypt_loli.loli_onebot.module.WSSend
import io.github.crypt_loli.loli_onebot.server.LoliOneBotServer
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.launch

class ReverseWSClient(
    val system: LoliOneBotServer,
    val wrapped: DefaultWebSocketServerSession
): WSSend {

    val api = OneBotApi(this, system.handler)

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