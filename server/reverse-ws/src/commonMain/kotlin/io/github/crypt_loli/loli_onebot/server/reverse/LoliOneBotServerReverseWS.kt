package io.github.crypt_loli.loli_onebot.server.reverse

import io.github.crypt_loli.loli_onebot.server.OneBotServer
import io.github.crypt_loli.loli_onebot.server.reverse.module.ReverseWSConnection
import io.github.crypt_loli.loli_onebot.utils.produceOne
import io.ktor.client.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import kotlinx.coroutines.launch

/**
 * 实现端主动连接应用端 (反向WS)
 *
 * @param server 实现端实例
 * @param url 应用端地址, 以 `ws://` 或 `wss://` 开头
 * @param accessToken 鉴权令牌
 */
class LoliOneBotServerReverseWS(
    val server: OneBotServer,
    val url: String,
    val accessToken: String? = null,
) {
    val httpClient = HttpClient {
        install(WebSockets)
    }


    var connection: ReverseWSConnection? = null
        private set

    /**
     * 连接
     *
     * @param await 是否等待直到断开
     */
    suspend fun connect(await: Boolean = true) = produceOne<ReverseWSConnection> {
        disconnect()

        server.scope.launch {
            httpClient.webSocket(
                urlString = url,
                request = {
                    accessToken?.takeIf { it.isNotEmpty() }?.also {
                        bearerAuth(it)
                    }
                }
            ) {
                val connection = ReverseWSConnection(server, this)
                send(connection)
                server.onConnection(connection.api)
                this@LoliOneBotServerReverseWS.connection = connection

                connection.job.join()

                server.onDisconnect(connection.api, connection.disconnectReason)
            }
        }
    }
        .apply { if (await) job.join() }

    suspend fun disconnect() {
        connection?.disconnect()
        connection = null
    }
}
