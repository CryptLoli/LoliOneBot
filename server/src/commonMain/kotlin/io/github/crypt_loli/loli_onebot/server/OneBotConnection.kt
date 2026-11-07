package io.github.crypt_loli.loli_onebot.server

import io.github.crypt_loli.loli_onebot.entity.ws.Response
import io.github.crypt_loli.loli_onebot.module.WSSend
import io.github.crypt_loli.loli_onebot.utils.jsonSend

/**
 * OneBot 客户端实例
 *
 * @param server 所属实现端
 * @param owner 底层连接
 */
class OneBotConnection(
    val server: OneBotServer,
    val owner: WSSend
) {

    suspend fun push(text: String) = owner.send(text)

    suspend inline fun <reified T> push(body: T) {
        push(jsonSend.encodeToString(body))
    }

    suspend fun respond(response: Response) = push(response)

    suspend fun disconnect() = owner.disconnect()
}
