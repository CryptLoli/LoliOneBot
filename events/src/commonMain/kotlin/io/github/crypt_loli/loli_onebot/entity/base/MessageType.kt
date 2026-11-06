package io.github.crypt_loli.loli_onebot.entity.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class MessageType {
    /**
     * 私聊消息
     */
    @SerialName("private")
    Private,
    /**
     * 群消息
     */
    @SerialName("group")
    Group
}