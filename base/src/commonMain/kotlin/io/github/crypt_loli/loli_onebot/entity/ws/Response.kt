package io.github.crypt_loli.loli_onebot.entity.ws

import io.github.crypt_loli.loli_onebot.utils.jsonReceive
import io.github.crypt_loli.loli_onebot.utils.jsonSend
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonObject

/**
 * @param status 状态描述
 * @param code 状态码
 * @param message 错误消息, 仅在 API 调用失败时有该字段
 * @param data 数据, 仅在 API 调用成功时有该字段
 * @param echo 回声
 */
@Serializable
data class Response(
    val status: Status = Status.Ok,
    @SerialName("retcode")
    val code: Int = StatusCode.Ok,
    @SerialName("msg")
    val message: String = "",
    val data: JsonElement = JsonNull,
    val echo: String? = null
) {
    enum class Status {
        @SerialName("ok")
        Ok,
        @SerialName("async")
        Async,
        @SerialName("failed")
        Failed
    }

    inline fun <reified T> decode(): T {
        require(status == Status.Ok) { message }

        return jsonReceive.decodeFromJsonElement<T>(data)
    }

    fun encodeToString(request: Request) = jsonSend
        .encodeToJsonElement(this)
        .jsonObject
        .let { encode ->
            request.echo
                ?.let { encode + JsonObject(mapOf("echo" to JsonPrimitive(it))) }
                ?: (encode - "echo")
        }
}