package io.github.crypt_loli.loli_onebot.entity.api.friend

import io.github.crypt_loli.loli_onebot.entity.api.ApiBase
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ApiFriendList: ApiBase("get_friend_list")

@Serializable
class ApiUnidirectionalFriendList: ApiBase("get_unidirectional_friend_list")

@Serializable
data class ApiFriendDelete(
    val params: Params
): ApiBase("delete_friend") {
    @Serializable
    data class Params(
        @SerialName("user_id")
        val userId: Long
    )
}

@Serializable
data class ApiUnidirectionalFriendDelete(
    val params: Params
): ApiBase("delete_unidirectional_friend") {
    @Serializable
    data class Params(
        @SerialName("user_id")
        val userId: Long
    )
}