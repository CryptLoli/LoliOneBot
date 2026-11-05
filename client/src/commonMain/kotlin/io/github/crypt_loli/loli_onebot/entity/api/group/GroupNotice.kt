package io.github.crypt_loli.loli_onebot.entity.api.group

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiGroupNoticeList(
    val action: String = "_get_group_notice",
    val params: Params
) {
    @Serializable
    data class Params(
        @SerialName("group_id")
        val groupId: Long
    )
}

@Serializable
data class DeleteGroupNoticeApi(
    val action: String = "_del_group_notice",
    val params: Params
) {
    @Serializable
    data class Params(
        @SerialName("group_id")
        val groupId: Long,
        @SerialName("notice_id")
        val noticeId: String
    )
}

@Serializable
internal data class ReleaseGroupNoticeApi(
    val action: String = "_send_group_notice",
    val params: Params
) {
    @Serializable
    data class Params(
        @SerialName("group_id")
        val groupId: Long,
        val content: String,
        val image: String
    )
}