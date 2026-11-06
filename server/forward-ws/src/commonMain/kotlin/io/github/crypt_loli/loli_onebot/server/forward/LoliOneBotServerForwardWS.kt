package io.github.crypt_loli.loli_onebot.server.forward

import io.github.crypt_loli.loli_onebot.server.OneBotServer
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.PipelineCall
import io.ktor.server.response.respond

/**
 * @param server 实现端实例
 * @param accessToken 鉴权令牌, 可以动态更换. 更换后立刻对新客户端生肖, 不会踢出已经完成鉴权的客户端
 */
class LoliOneBotServerForwardWS(
    val server: OneBotServer,
    var accessToken: String? = null,
) {
    suspend fun onAnyWSConnected(call: PipelineCall) {
        run checkAccessToken@ {
            accessToken?.also { accessToken ->
                val clientToken = call.request.headers[HttpHeaders.Authorization] ?: run {
                    call.respond(HttpStatusCode.Unauthorized)
                    return
                }
                if (clientToken != "Bearer $accessToken") { call.respond(HttpStatusCode.Forbidden) }
            }
        }
    }
}
