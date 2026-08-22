package io.github.crypt_loli.loli_onebot.entity.api.group

import io.github.crypt_loli.loli_onebot.entity.api.ApiBase
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiGroupList(
    val action: String = "get_group_list"
): ApiBase()

@Serializable
data class ApiGroupInfo(
    val params: Params,
    val action: String = "get_group_info"
): ApiBase() {
    @Serializable
    data class Params(
        @SerialName("group_id")
        val groupId: Long
    )
}

@Serializable
data class ApiGroupMemberList(
    val params: Params,
    val action: String = "get_group_member_list"
): ApiBase() {
    @Serializable
    data class Params(
        @SerialName("group_id")
        val groupId: Long
    )
}

@Serializable
data class ApiGroupMemberInfo(
    val params: Params,
    val action: String = "get_group_member_info"
): ApiBase() {
    @Serializable
    data class Params(
        @SerialName("group_id")
        val groupId: Long,
        @SerialName("user_id")
        val userId: Long
    )
}

@Serializable
data class ApiGroupLeave(
    val params: Params,
    val action: String = "set_group_leave"
): ApiBase() {
    @Serializable
    data class Params(
        @SerialName("group_id")
        val groupId: Long,
        @SerialName("is_dismiss")
        val isDismiss: Boolean
    )
}

@Serializable
data class ApiGroupMemberSign(
    val params: Params,
    val action: String = "set_group_sign"
): ApiBase() {
    @Serializable
    data class Params(
        @SerialName("group_id")
        val groupId: Long
    )
}