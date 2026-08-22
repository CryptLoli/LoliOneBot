package io.github.crypt_loli.loli_onebot.entity_dsl.event

import io.github.crypt_loli.loli_onebot.api_dsl.setFriendRequest
import io.github.crypt_loli.loli_onebot.api_dsl.setGroupRequest
import io.github.crypt_loli.loli_onebot.entity.event.request.OneBotRequestBeAddFriend
import io.github.crypt_loli.loli_onebot.entity.event.request.OneBotRequestBeInviteGroup
import io.github.crypt_loli.loli_onebot.entity.event.request.OneBotRequestJoinGroup

/**
 * 同意好友请求
 *
 * @param remark 设置好友备注
 */
suspend fun OneBotRequestBeAddFriend.accept(remark: String = "") = api.setFriendRequest(flag, true, remark)

/**
 * 拒绝好友请求
 */
suspend fun OneBotRequestBeAddFriend.deny() = api.setFriendRequest(flag, false)

/**
 * 同意入群邀请
 */
suspend fun OneBotRequestBeInviteGroup.accept() = api.setGroupRequest(flag, "invite", true)

/**
 * 拒绝入群邀请
 */
suspend fun OneBotRequestBeInviteGroup.deny(reason: String? = null) = api.setGroupRequest(flag, "invite", false)

/**
 * 同意入群请求
 */
suspend fun OneBotRequestJoinGroup.accept() = api.setGroupRequest(flag, "add", true)

/**
 * 拒绝入群请求
 *
 * @param reason 拒绝理由
 */
suspend fun OneBotRequestJoinGroup.deny(reason: String? = null) = api.setGroupRequest(flag, "add", false, reason)