package io.github.crypt_loli.loli_onebot.api_dsl.group

import io.github.crypt_loli.loli_onebot.OneBotClientApi
import io.github.crypt_loli.loli_onebot.entity.api.group.ApiGroupSetSpecialTitle

/**
 * 设置群组专属头衔
 *
 * @param groupId 群组
 * @param userId 账号
 * @param title 专属头衔
 */
suspend fun OneBotClientApi.setGroupSpecialTitle(groupId: Long, userId: Long, title: String) = send(
    entity = ApiGroupSetSpecialTitle(ApiGroupSetSpecialTitle.Params(groupId, userId, title))
)

/**
 * 清除群组专属头衔
 *
 * @param groupId 群组
 * @param userId 账号
 */
suspend fun OneBotClientApi.clearGroupSpecialTitle(groupId: Long, userId: Long) = setGroupSpecialTitle(groupId, userId, "")