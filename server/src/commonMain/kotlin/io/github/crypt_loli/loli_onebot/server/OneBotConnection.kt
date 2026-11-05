package io.github.crypt_loli.loli_onebot.server

import io.github.crypt_loli.loli_onebot.entity.ws.Response
import io.github.crypt_loli.loli_onebot.module.WSSend
import io.github.crypt_loli.loli_onebot.utils.jsonSend

/**
 * 一条已经连上的 OneBot 应用。实现端用它回包，也可以只推给这一条连接
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
