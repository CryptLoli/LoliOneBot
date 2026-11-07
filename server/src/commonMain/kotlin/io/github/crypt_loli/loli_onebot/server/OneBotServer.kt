package io.github.crypt_loli.loli_onebot.server

import io.github.crypt_loli.loli_onebot.entity.Account
import io.github.crypt_loli.loli_onebot.entity.event.OneBotEvent
import io.github.crypt_loli.loli_onebot.utils.event.EventRegistry
import io.github.crypt_loli.loli_onebot.utils.jsonSend
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.JsonObject

/**
 * OneBot 实现端
 *
 * @param listener 事件监听
 * @param eventRegistry 事件注册器
 * @param account 当前登录账号信息
 * @param scope 读取连接和分发 action 使用的协程作用域
 */
class OneBotServer(
    val listener: OneBotServerListener,
    val eventRegistry: EventRegistry = EventRegistry(),
    var account: Account = Account(),
    val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
) {
    val connections = mutableSetOf<OneBotConnection>()
    val handler = OneBotServerHandler(listener)

    suspend fun onConnection(api: OneBotConnection, block: suspend (connection: OneBotConnection) -> Unit = {}) {
        listener.onAppOnline(api)
        if (api.owner.isConnected) {
            connections += api
            block(api)
        }
    }

    suspend fun onDisconnect(api: OneBotConnection, reason: Throwable? = null) {
        connections -= api
        listener.onAppOffline(api, reason)
    }

    /**
     * 向所有客户端广播事件
     *
     * @param raw 要广播的原始字符 (无效的数据可能无法被解析)
     */
    suspend fun broadcast(raw: String) {
        connections.toList().forEach { app ->
            runCatching { app.push(raw) }
        }
    }

    /**
     * 向所有客户端广播事件
     *
     * @param raw 要广播的原始 json 数据 (无效的数据可能无法被解析)
     */
    suspend fun broadcast(raw: JsonObject) = broadcast(jsonSend.encodeToString(raw))
    /**
     * 向所有客户端广播事件
     *
     * @param event 要广播的事件
     * @param account 事件所关联的账号 只有特殊的多账号OneBot服务端才需要传入(非OneBot标准实现)
     */
    suspend inline fun <reified Event: OneBotEvent> broadcast(event: Event, account: Account = this.account) = broadcast(eventRegistry.encode<Event>(event, account))
}
