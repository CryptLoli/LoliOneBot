package io.github.crypt_loli.loli_onebot.client

import io.github.crypt_loli.loli_onebot.client.module.ForwardWSClient
import io.github.crypt_loli.loli_onebot.client.sdk.OneBotWSForwardListener
import io.github.crypt_loli.loli_onebot.utils.OneBotMessageHandler
import io.github.crypt_loli.loli_onebot.utils.SuspendedRequests
import io.github.crypt_loli.loli_onebot.utils.produceOne
import io.ktor.client.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

/**
 * 创建一个 OneBot 正向 WebSocket 客户端实例
 *
 * @param listener 事件下发类
 * @param url 连接地址 必须 `ws://`或 `wss://` 开头
 * @param accessToken 鉴权令牌
 * @param scope 线程池
 */
class LoliOneBotClient(
    val listener: OneBotWSForwardListener,
    val url: String = "ws://localhost:6700/",
    val accessToken: String? = null,
    val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
) {
    val httpClient = HttpClient {
        install(WebSockets)
    }
    val requests = SuspendedRequests()
    val handler = OneBotMessageHandler(listener, requests)

    var connection: ForwardWSClient? = null
        private set

    /**
     * 连接
     *
     * @param await 是否等待直到断开
     */
    suspend fun connect(await: Boolean = false) = produceOne<ForwardWSClient> {
        disconnect()

        scope.launch {
            httpClient.webSocket(
                urlString = url,
                request = {
                    accessToken?.takeIf { it.isNotEmpty() }?.also {
                        bearerAuth(it)
                    }
                }
            ) {
                val connection = ForwardWSClient(this@LoliOneBotClient, this)
                send(connection)
                listener.onConnect()
                this@LoliOneBotClient.connection = connection

                connection.job.join()

                listener.onDisconnect(connection.disconnectReason)
            }
        }
    }
        .apply { if (await) job.join() }

    suspend fun disconnect() {
        connection?.disconnect()
        connection = null
    }
}
