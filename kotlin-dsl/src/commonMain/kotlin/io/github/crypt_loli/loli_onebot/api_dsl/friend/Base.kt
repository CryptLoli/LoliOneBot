package io.github.crypt_loli.loli_onebot.api_dsl.friend

import io.github.crypt_loli.loli_onebot.OneBotApi
import io.github.crypt_loli.loli_onebot.entity.api.friend.ApiFriendDelete
import io.github.crypt_loli.loli_onebot.entity.api.friend.ApiFriendList
import io.github.crypt_loli.loli_onebot.entity.api.friend.ApiUnidirectionalFriendList
import io.github.crypt_loli.loli_onebot.entity.base.FriendInfo

/**
 * 获取好友列表
 */
suspend fun OneBotApi.getFriendList() = sendWaiting(
    entity = ApiFriendList()
).decode<List<FriendInfo>>()

/**
 * 获取单向好友列表
 */
suspend fun OneBotApi.getUnidirectionalFriendList() = sendWaiting(
    entity = ApiUnidirectionalFriendList()
).decode<List<FriendInfo>>()

/**
 * 删除好友
 *
 * @param userId 对方账号
 */
suspend fun OneBotApi.deleteFriend(userId: Long) = send(
    entity = ApiFriendDelete(ApiFriendDelete.Params(userId))
)

/**
 * 删除单向好友
 *
 * @param userId 对方账号
 */
suspend fun OneBotApi.deleteUnidirectionalFriend(userId: Long) = send(
    entity = ApiFriendDelete(ApiFriendDelete.Params(userId))
)