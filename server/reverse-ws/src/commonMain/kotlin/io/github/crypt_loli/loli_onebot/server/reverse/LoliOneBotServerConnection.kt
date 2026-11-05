package io.github.crypt_loli.loli_onebot.server.reverse

import io.github.crypt_loli.loli_onebot.server.OneBotServer
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
class LoliOneBotServerConnection(
    val server: OneBotServer,
    val url: String,
    val accessToken: String? = null,
) {
    val httpClient = HttpClient {
        install(WebSockets)
    }


    var connection: ReverseWSApp? = null
        private set

    /**
     * 连接
     *
     * @param await 是否等待直到断开
     */
    suspend fun connect(await: Boolean = true) {
        disconnect()

        httpClient.webSocket(
            urlString = url,
            request =  {
                accessToken?.takeIf { it.isNotEmpty() }?.also {
                    bearerAuth(it)
                }
            }
        ) {
            connection = ReverseWSApp(server, this)
                .also { connection ->
                    server.scope.launch {
                        server.onConnection(connection.api)
                        connection.job.join()
                        server.onDisconnect(connection.api, connection.disconnectReason)
                    }

                    if (await) connection.job.join()
                }
        }
    }

    suspend fun disconnect() {
        connection?.disconnect()
        connection = null
    }
}
