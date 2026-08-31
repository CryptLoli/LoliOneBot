package io.github.crypt_loli.loli_onebot.api_dsl.group

import io.github.crypt_loli.loli_onebot.OneBotApi
import io.github.crypt_loli.loli_onebot.entity.api.group.ApiGroupEssenceMessageDelete
import io.github.crypt_loli.loli_onebot.entity.api.group.ApiGroupEssenceMessageList
import io.github.crypt_loli.loli_onebot.entity.api.group.ApiGroupEssenceMessageSet
import io.github.crypt_loli.loli_onebot.entity.api.group.ApiResponseGroupEssenceMessage

/**
 * 获取群精华消息列表
 *
 * @param groupId 群聊Id
 */
suspend fun OneBotApi.getGroupEssenceMessages(groupId: Long) = sendWaiting(
    entity = ApiGroupEssenceMessageList(ApiGroupEssenceMessageList.Params(groupId))
).decode<List<ApiResponseGroupEssenceMessage>>()

/**
 * 将某条消息设置为群精华消息
 *
 * @param messageId 消息Id
 */
suspend fun OneBotApi.setGroupEssenceMessage(messageId: Long) = sendWaiting(
    entity = ApiGroupEssenceMessageSet(ApiGroupEssenceMessageSet.Params(messageId))
)

/**
 * 将某条消息移出群精华消息列表
 *
 * @param messageId 消息Id
 */
suspend fun OneBotApi.deleteGroupEssenceMessage(messageId: Long) = sendWaiting(
    entity = ApiGroupEssenceMessageDelete(ApiGroupEssenceMessageDelete.Params(messageId))
)