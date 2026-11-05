package io.github.crypt_loli.loli_onebot.entity.api.group

import io.github.crypt_loli.loli_onebot.entity.api.ApiBase
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiGroupManagerKickMember(
    val params: Params
): ApiBase("set_group_kick") {
    @Serializable
    data class Params(
        @SerialName("group_id")
        val groupId: Long,
        @SerialName("user_id")
        val userId: Long,
        @SerialName("reject_add_request")
        val rejectAddRequest: Boolean
    )
}

@Serializable
data class ApiGroupManagerMuteMember(
    val params: Params
): ApiBase("set_group_ban") {
    @Serializable
    data class Params(
        @SerialName("group_id")
        val groupId: Long,
        @SerialName("user_id")
        val userId: Long,
        val duration: Int
    )
}

@Serializable
data class ApiGroupManagerWholeMute(
    val params: Params
): ApiBase("set_group_whole_ban") {
    @Serializable
    data class Params(
        @SerialName("group_id")
        val groupId: Long,
        val enable: Boolean
    )
}

@Serializable
data class ApiGroupManagerSetMemberCard(
    val params: Params
): ApiBase("set_group_card") {
    @Serializable
    data class Params(
        @SerialName("group_id")
        val groupId: Long,
        @SerialName("user_id")
        val userId: Long,
        val card: String,
    )
}

@Serializable
data class ApiGroupManagerSetName(
    val params: Params
): ApiBase("set_group_name") {
    @Serializable
    data class Params(
        @SerialName("group_id")
        val groupId: Long,
        @SerialName("group_name")
        val groupName: String
    )
}

@Serializable
data class ApiGroupManagerSetAdmin(
    val params: Params
): ApiBase("set_group_admin") {
    @Serializable
    data class Params(
        @SerialName("group_id")
        val groupId: Long,
        @SerialName("user_id")
        val userId: Long,
        val enable: Boolean
    )
}

@Serializable
data class ApiGroupManagerSetAvatar(
    val params: Params
): ApiBase("set_group_portrait") {
    @Serializable
    data class Params(
        @SerialName("group_id")
        val groupId: Long,
        val file: String
    )
}