package io.github.crypt_loli.loli_onebot.api_dsl

import io.github.crypt_loli.loli_onebot.OneBotApi
import io.github.crypt_loli.loli_onebot.entity.api.message.ApiMessageGet
import io.github.crypt_loli.loli_onebot.entity.api.message.ApiMessageMarkAsRead
import io.github.crypt_loli.loli_onebot.entity.api.message.ApiMessageRevoke
import io.github.crypt_loli.loli_onebot.entity.api.message.ApiResponseMessageGet

/**
 * 撤回消息
 *
 * @param id 消息ID
 */
suspend fun OneBotApi.revokeMessage(id: Long) = send(
    entity = ApiMessageRevoke(ApiMessageRevoke.Params(id))
)

/**
 * 置消息已读取
 *
 * @param id 消息ID
 */
suspend fun OneBotApi.markAsRead(id: Long) = send(
    entity = ApiMessageMarkAsRead(ApiMessageMarkAsRead.Params(id))
)

/**
 * 根据消息ID获取消息信息
 *
 * @param id 消息ID
 */
suspend fun OneBotApi.getMessage(id: Long) = sendWaiting(
    entity = ApiMessageGet(ApiMessageGet.Params(id))
).decode<ApiResponseMessageGet>()