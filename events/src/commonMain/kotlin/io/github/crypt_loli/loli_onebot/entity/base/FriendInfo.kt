package io.github.crypt_loli.loli_onebot.entity.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FriendInfo(
    @SerialName("user_id")
    val userId: Long,
    @SerialName("nickname")
    val name: String,
    val remark: String = ""
)
