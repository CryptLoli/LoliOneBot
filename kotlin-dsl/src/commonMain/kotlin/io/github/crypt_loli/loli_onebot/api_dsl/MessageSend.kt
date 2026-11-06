package io.github.crypt_loli.loli_onebot.api_dsl

import io.github.crypt_loli.loli_onebot.OneBotClientApi
import io.github.crypt_loli.loli_onebot.entity.api.message.*
import io.github.crypt_loli.loli_onebot.entity.array.OneBotMessage
import io.github.crypt_loli.loli_onebot.entity.array.RawOneBotMessage
import io.github.crypt_loli.loli_onebot.entity_dsl.entity.unformat
import kotlin.jvm.JvmName

suspend fun OneBotClientApi.sendPrivateMessage(userId: Long, message: String) = sendWaiting(
    entity = ApiMessagePrivateRaw(ApiMessagePrivateRaw.Params(userId, message))
).decode<ApiResponseMessageSend>().id

@JvmName(name = "sendPrivateMessageUnformat")
suspend fun OneBotClientApi.sendPrivateMessage(userId: Long, data: RawOneBotMessage) = sendWaiting(
    entity = ApiMessagePrivateArray(ApiMessagePrivateArray.Params(userId, data))
).decode<ApiResponseMessageSend>().id

suspend fun OneBotClientApi.sendPrivateMessage(userId: Long, data: OneBotMessage) = sendPrivateMessage(userId, data.unformat)

suspend fun OneBotClientApi.sendGroupMessage(groupId: Long, message: String) = sendWaiting(
    entity = ApiMessageGroupRaw(ApiMessageGroupRaw.Params(groupId, message))
).decode<ApiResponseMessageSend>().id

suspend fun OneBotClientApi.sendGroupMessage(groupId: Long, data: RawOneBotMessage) = sendWaiting(
    entity = ApiMessageGroupArray(ApiMessageGroupArray.Params(groupId, data))
).decode<ApiResponseMessageSend>().id

@JvmName(name = "sendGroupMessageUnformat")
suspend fun OneBotClientApi.sendGroupMessage(groupId: Long, data: OneBotMessage) = sendGroupMessage(groupId, data.unformat)

suspend fun OneBotClientApi.sendPrivateMessageAsync(userId: Long, message: String) = send(
    entity = ApiMessagePrivateRaw(ApiMessagePrivateRaw.Params(userId, message))
)

suspend fun OneBotClientApi.sendPrivateMessageAsync(userId: Long, data: RawOneBotMessage) = send(
    entity = ApiMessagePrivateArray(ApiMessagePrivateArray.Params(userId, data))
)

@JvmName(name = "sendPrivateMessageUnformatAsync")
suspend fun OneBotClientApi.sendPrivateMessageAsync(userId: Long, data: OneBotMessage) = sendPrivateMessageAsync(userId, data.unformat)

suspend fun OneBotClientApi.sendGroupMessageAsync(groupId: Long, message: String) = send(
    entity = ApiMessageGroupRaw(ApiMessageGroupRaw.Params(groupId, message))
)

suspend fun OneBotClientApi.sendGroupMessageAsync(groupId: Long, data: RawOneBotMessage) = send(
    entity = ApiMessageGroupArray(ApiMessageGroupArray.Params(groupId, data))
)

@JvmName(name = "sendGroupMessageUnformatAsync")
suspend fun OneBotClientApi.sendGroupMessageAsync(groupId: Long, data: OneBotMessage) = sendGroupMessageAsync(groupId, data.unformat)