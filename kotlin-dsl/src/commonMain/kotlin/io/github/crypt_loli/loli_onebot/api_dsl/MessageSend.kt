package io.github.crypt_loli.loli_onebot.api_dsl

import io.github.crypt_loli.loli_onebot.OneBotApi
import io.github.crypt_loli.loli_onebot.entity.api.message.ApiMessageGroupArray
import io.github.crypt_loli.loli_onebot.entity.api.message.ApiMessageGroupRaw
import io.github.crypt_loli.loli_onebot.entity.api.message.ApiMessagePrivateArray
import io.github.crypt_loli.loli_onebot.entity.api.message.ApiMessagePrivateRaw
import io.github.crypt_loli.loli_onebot.entity.api.message.ApiResponseMessageSend
import io.github.crypt_loli.loli_onebot.entity.array.ArrayMessage

suspend fun OneBotApi.sendPrivateMessage(userId: Long, message: String) = sendWaiting(
    entity = ApiMessagePrivateRaw(ApiMessagePrivateRaw.Params(userId, message))
).decode<ApiResponseMessageSend>().id

suspend fun OneBotApi.sendPrivateMessage(userId: Long, data: List<ArrayMessage>) = sendWaiting(
    entity = ApiMessagePrivateArray(ApiMessagePrivateArray.Params(userId, data))
).decode<ApiResponseMessageSend>().id

suspend fun OneBotApi.sendGroupMessage(groupId: Long, message: String) = sendWaiting(
    entity = ApiMessageGroupRaw(ApiMessageGroupRaw.Params(groupId, message))
).decode<ApiResponseMessageSend>().id

suspend fun OneBotApi.sendGroupMessage(groupId: Long, data: List<ArrayMessage>) = sendWaiting(
    entity = ApiMessageGroupArray(ApiMessageGroupArray.Params(groupId, data))
).decode<ApiResponseMessageSend>().id

suspend fun OneBotApi.sendPrivateMessageAsync(userId: Long, message: String) = send(
    entity = ApiMessagePrivateRaw(ApiMessagePrivateRaw.Params(userId, message))
)

suspend fun OneBotApi.sendPrivateMessageAsync(userId: Long, data: List<ArrayMessage>) = send(
    entity = ApiMessagePrivateArray(ApiMessagePrivateArray.Params(userId, data))
)

suspend fun OneBotApi.sendGroupMessageAsync(groupId: Long, message: String) = send(
    entity = ApiMessageGroupRaw(ApiMessageGroupRaw.Params(groupId, message))
)

suspend fun OneBotApi.sendGroupMessageAsync(groupId: Long, data: List<ArrayMessage>) = send(
    entity = ApiMessageGroupArray(ApiMessageGroupArray.Params(groupId, data))
)