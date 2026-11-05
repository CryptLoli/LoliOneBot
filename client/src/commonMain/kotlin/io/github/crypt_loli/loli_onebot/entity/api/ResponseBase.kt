package io.github.crypt_loli.loli_onebot.entity.api

import io.github.crypt_loli.loli_onebot.utils.jsonReceive
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.decodeFromJsonElement

/**
 * @param status 状态描述
 * @param code 状态码
 * @param message 错误消息, 仅在 API 调用失败时有该字段
 * @param data 数据, 仅在 API 调用成功时有该字段
 * @param echo 回声
 */
@Serializable
data class ResponseBase(
    val status: Status,
    @SerialName("retcode")
    val code: Int,
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
}