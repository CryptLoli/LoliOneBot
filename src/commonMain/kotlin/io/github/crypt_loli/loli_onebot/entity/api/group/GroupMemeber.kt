package io.github.crypt_loli.loli_onebot.entity.api.group

import io.github.crypt_loli.loli_onebot.entity.api.ApiBase
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ApiGroupList: ApiBase("get_group_list")

@Serializable
data class ApiGroupInfo(
    val params: Params
): ApiBase("get_group_info") {
    @Serializable
    data class Params(
        @SerialName("group_id")
        val groupId: Long
    )
}

@Serializable
data class ApiGroupMemberList(
    val params: Params
): ApiBase("get_group_member_list") {
    @Serializable
    data class Params(
        @SerialName("group_id")
        val groupId: Long
    )
}

@Serializable
data class ApiGroupMemberInfo(
    val params: Params
): ApiBase("get_group_member_info") {
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
    val params: Params
): ApiBase("set_group_leave") {
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
    val params: Params
): ApiBase("set_group_sign") {
    @Serializable
    data class Params(
        @SerialName("group_id")
        val groupId: Long
    )
}