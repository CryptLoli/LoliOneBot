package io.github.crypt_loli.loli_onebot.server.module

import io.github.crypt_loli.loli_onebot.OneBotClientApi
import io.github.crypt_loli.loli_onebot.module.WSSend
import io.github.crypt_loli.loli_onebot.server.LoliOneBotClientReverseWS
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.launch

class ReverseWSConnection(
    val system: LoliOneBotClientReverseWS,
    val wrapped: DefaultWebSocketServerSession
): WSSend {

    val api = OneBotClientApi(this, system.handler)

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