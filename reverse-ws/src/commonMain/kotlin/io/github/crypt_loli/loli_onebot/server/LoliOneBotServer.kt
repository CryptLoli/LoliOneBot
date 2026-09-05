package io.github.crypt_loli.loli_onebot.server

import io.github.crypt_loli.loli_onebot.server.module.ReverseWSClient
import io.github.crypt_loli.loli_onebot.server.sdk.OneBotWSReverseListener
import io.github.crypt_loli.loli_onebot.utils.OneBotMessageHandler
import io.github.crypt_loli.loli_onebot.utils.SuspendedRequests
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

/**
 * 创建一个 OneBot 服务端实例
 *
 * @param listener 事件下发类
 * @param scope 线程池
 */
class LoliOneBotServer(
    val listener: OneBotWSReverseListener,
    val scope: CoroutineScope = CoroutineScope(Dispatchers.Default)
) {
    val clients = mutableSetOf<ReverseWSClient>()
    val requests = SuspendedRequests()
    val handler = OneBotMessageHandler(listener, requests)
}