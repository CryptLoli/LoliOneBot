package io.github.crypt_loli.loli_onebot.server

import io.github.crypt_loli.loli_onebot.server.action.ActionResponse
import kotlinx.serialization.json.JsonObject

interface OneBotServerListener {
    /**
     * 任意客户端上线
     *
     * @param api 客户端操作实例
     */
    suspend fun onClientOnline(api: OneBotConnection) {}

    /**
     * 任意客户端离线
     *
     * @param api 客户端操作实例
     * @param reason 原因
     */
    suspend fun onClientOffline(api: OneBotConnection, reason: Throwable? = null) {}

    /**
     * 客户端执行任意指令
     *
     * @param api 客户端操作实例
     * @param action 指令名
     * @param params 指令参数
     *
     * @return 要返回的内容, 亦可通过抛出 `ActionResponseOverException`
     */
    suspend fun onAction(api: OneBotConnection, action: String, params: JsonObject): ActionResponse
}
