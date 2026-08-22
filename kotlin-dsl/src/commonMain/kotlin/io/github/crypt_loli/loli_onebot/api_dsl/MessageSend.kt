package io.github.crypt_loli.loli_onebot.api_dsl

import io.github.crypt_loli.loli_onebot.OneBotApi
import io.github.crypt_loli.loli_onebot.entity.api.message.*
import io.github.crypt_loli.loli_onebot.entity.array.OneBotMessage

suspend fun OneBotApi.sendPrivateMessage(userId: Long, message: String) = sendWaiting(
    entity = ApiMessagePrivateRaw(ApiMessagePrivateRaw.Params(userId, message))
).decode<ApiResponseMessageSend>().id

suspend fun OneBotApi.sendPrivateMessage(userId: Long, data: OneBotMessage) = sendWaiting(
    entity = ApiMessagePrivateArray(ApiMessagePrivateArray.Params(userId, data))
).decode<ApiResponseMessageSend>().id

suspend fun OneBotApi.sendGroupMessage(groupId: Long, message: String) = sendWaiting(
    entity = ApiMessageGroupRaw(ApiMessageGroupRaw.Params(groupId, message))
).decode<ApiResponseMessageSend>().id

suspend fun OneBotApi.sendGroupMessage(groupId: Long, data: OneBotMessage) = sendWaiting(
    entity = ApiMessageGroupArray(ApiMessageGroupArray.Params(groupId, data))
).decode<ApiResponseMessageSend>().id

suspend fun OneBotApi.sendPrivateMessageAsync(userId: Long, message: String) = send(
    entity = ApiMessagePrivateRaw(ApiMessagePrivateRaw.Params(userId, message))
)

suspend fun OneBotApi.sendPrivateMessageAsync(userId: Long, data: OneBotMessage) = send(
    entity = ApiMessagePrivateArray(ApiMessagePrivateArray.Params(userId, data))
)

suspend fun OneBotApi.sendGroupMessageAsync(groupId: Long, message: String) = send(
    entity = ApiMessageGroupRaw(ApiMessageGroupRaw.Params(groupId, message))
)

suspend fun OneBotApi.sendGroupMessageAsync(groupId: Long, data: OneBotMessage) = send(
    entity = ApiMessageGroupArray(ApiMessageGroupArray.Params(groupId, data))
)