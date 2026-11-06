package io.github.crypt_loli.loli_onebot.server.forward

import io.github.crypt_loli.loli_onebot.server.OneBotConnection
import io.github.crypt_loli.loli_onebot.server.OneBotServer
import io.github.crypt_loli.loli_onebot.server.forward.module.ForwardWSConnection
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

/**
 * @param server 实现端实例
 * @param accessToken 鉴权令牌, 可以动态更换. 更换后立刻对新客户端生肖, 不会踢出已经完成鉴权的客户端
 */
class LoliOneBotServerForwardWS(
    val server: OneBotServer,
    var accessToken: String? = null,
) {

    val connections = mutableSetOf<ForwardWSConnection>()

    suspend fun onAnyWSConnected(call: PipelineCall) {
        run checkAccessToken@ {
            accessToken?.also { accessToken ->
                val clientToken = call.request.headers[HttpHeaders.Authorization]?.removePrefix("Bearer ")
                    ?: call.request.queryParameters["access_token"]
                    ?: run {
                    call.respond(HttpStatusCode.Unauthorized)
                    return
                }
                if (clientToken != accessToken) { call.respond(HttpStatusCode.Forbidden) }
            }
        }
    }

    suspend fun onConnection(connection: ForwardWSConnection, block: suspend (connection: OneBotConnection) -> Unit = {}) {
        server.onConnection(connection.api) {
            connections += connection
            block(it)
        }
    }

    suspend fun onDisconnect(connection: ForwardWSConnection, reason: Throwable? = null) {
        server.onDisconnect(connection.api, reason)
        connections -= connection
    }
}
