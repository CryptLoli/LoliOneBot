package io.github.crypt_loli.loli_onebot.server

import io.github.crypt_loli.loli_onebot.utils.jsonSend
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

/**
 * OneBot 实现端
 *
 * @param listener 事件监听
 * @param scope 读取连接和分发 action 使用的协程作用域
 */
class OneBotServer(
    val listener: OneBotActionListener,
    val scope: CoroutineScope = CoroutineScope(Dispatchers.Default)
) {
    val connections = mutableSetOf<OneBotConnection>()
    val handler = OneBotActionHandler(listener)

    suspend fun onConnection(api: OneBotConnection) {
        listener.onAppOnline(api)
        connections += api
    }

    suspend fun onDisconnect(api: OneBotConnection, reason: Throwable? = null) {
        connections -= api
        listener.onAppOffline(api, reason)
    }

    suspend fun broadcast(text: String) {
        connections.toList().forEach { app ->
            runCatching { app.push(text) }
        }
    }

    suspend inline fun <reified T> broadcast(body: T) {
        broadcast(jsonSend.encodeToString(body))
    }
}
