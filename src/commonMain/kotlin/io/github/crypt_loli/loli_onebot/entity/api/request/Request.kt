package io.github.crypt_loli.loli_onebot.entity.api.request

import io.github.crypt_loli.loli_onebot.entity.api.ApiBase
import kotlinx.serialization.Serializable

@Serializable
data class ApiRequestSetFriend(
    val params: Params,
    val action: String = "set_friend_add_request"
): ApiBase() {
    @Serializable
    data class Params(
        val flag: String,
        val approve: Boolean,
        val remark: String
    )
}

@Serializable
data class ApiRequestSetGroup(
    val params: Params,
    val action: String = "set_group_add_request"
): ApiBase() {
    @Serializable
    data class Params(
        val flag: String,
        val type: String,
        val approve: Boolean,
        val reason: String?
    )
}