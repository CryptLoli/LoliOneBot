package io.github.crypt_loli.loli_onebot.server.action

import io.github.crypt_loli.loli_onebot.entity.ws.Response
import kotlinx.serialization.json.JsonElement

abstract class ActionResponse {
    abstract fun warp(): Response
}

class ActionResponseOk(val body: JsonElement): ActionResponse() {
    override fun warp() = Response(data = body)
}

object ActionResponseAsync: ActionResponse() {
    override fun warp() = Response(status = Response.Status.Async)
}

class ActionResponseFailed(val code: Int, val message: String): ActionResponse() {
    override fun warp() = Response(status = Response.Status.Failed, code = code, message = message)
}

class ActionResponseOverException(val data: ActionResponse): Exception()