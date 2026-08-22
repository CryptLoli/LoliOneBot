package io.github.crypt_loli.loli_onebot.entity.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @param id 群号
 * @parm name 群名称
 * @param memberCount 成员数量
 * @param memberLimit 最大成员数量
 */
@Serializable
class GroupInfo(
    @SerialName("group_id")
    val id: Long,
    @SerialName("group_name")
    val name: String,
    @SerialName("member_count")
    val memberCount: Int,
    @SerialName("max_member_count")
    val memberLimit: Int,
)