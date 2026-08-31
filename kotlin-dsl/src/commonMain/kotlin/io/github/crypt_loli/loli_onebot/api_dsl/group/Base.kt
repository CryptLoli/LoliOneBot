package io.github.crypt_loli.loli_onebot.api_dsl.group

import io.github.crypt_loli.loli_onebot.OneBotApi
import io.github.crypt_loli.loli_onebot.entity.api.group.ApiGroupInfo
import io.github.crypt_loli.loli_onebot.entity.api.group.ApiGroupList
import io.github.crypt_loli.loli_onebot.entity.api.group.ApiGroupLeave
import io.github.crypt_loli.loli_onebot.entity.api.group.ApiGroupMemberInfo
import io.github.crypt_loli.loli_onebot.entity.api.group.ApiGroupMemberList
import io.github.crypt_loli.loli_onebot.entity.base.GroupInfo
import io.github.crypt_loli.loli_onebot.entity.base.GroupMemberInfo

/**
 * 获取群聊列表
 */
suspend fun OneBotApi.getGroupList() = sendWaiting(
    entity = ApiGroupList()
).decode<List<GroupInfo>>()

/**
 * 获取群聊具体信息
 *
 * @param groupId 群聊Id
 */
suspend fun OneBotApi.getGroupInfo(groupId: Long) = sendWaiting(
    entity = ApiGroupInfo(ApiGroupInfo.Params(groupId))
).decode<GroupInfo>()

/**
 * 获取群聊成员列表
 *
 * @param groupId 群聊Id
 */
suspend fun OneBotApi.getGroupMemberList(groupId: Long) = sendWaiting(
    entity = ApiGroupMemberList(ApiGroupMemberList.Params(groupId))
).decode<List<GroupMemberInfo>>()

/**
 * 获取群聊成员信息
 *
 * @param groupId 群聊Id
 * @param userId 成员Id
 */
suspend fun OneBotApi.getGroupMemberInfo(groupId: Long, userId: Long) = sendWaiting(
    entity = ApiGroupMemberInfo(ApiGroupMemberInfo.Params(groupId, userId))
).decode<GroupMemberInfo>()

/**
 * 退群
 *
 * @param isDismiss 解散保护锁 开启后群主身份调用则解散群
 */
suspend fun OneBotApi.leaveGroup(groupId: Long, isDismiss: Boolean = false) = send(
    entity = ApiGroupLeave(ApiGroupLeave.Params(groupId, isDismiss))
)