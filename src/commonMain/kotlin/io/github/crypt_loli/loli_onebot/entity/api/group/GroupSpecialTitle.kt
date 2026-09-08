package io.github.crypt_loli.loli_onebot.entity.api.group

import io.github.crypt_loli.loli_onebot.entity.api.ApiBase
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiGroupSetSpecialTitle(
    val params: Params
): ApiBase("set_group_special_title") {
    @Serializable
    data class Params(
        @SerialName("group_id")
        val groupId: Long,
        @SerialName("user_id")
        val userId: Long,
        @SerialName("special_title")
        val title: String
    )
}