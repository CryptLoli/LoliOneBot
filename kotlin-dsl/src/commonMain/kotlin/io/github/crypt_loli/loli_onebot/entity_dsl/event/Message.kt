package io.github.crypt_loli.loli_onebot.entity_dsl.event

import io.github.crypt_loli.loli_onebot.api_dsl.*
import io.github.crypt_loli.loli_onebot.entity.array.RawOneBotMessage
import io.github.crypt_loli.loli_onebot.entity_dsl.entity.hasMention
import io.github.crypt_loli.loli_onebot.entity_dsl.entity.replyMessageId
import io.github.crypt_loli.loli_onebot.events.*

suspend fun OneBotPrivateMessageEvent.reply(message: String) = clientApi.sendPrivateMessage(userId, message)
suspend fun OneBotPrivateMessageEvent.reply(data: RawOneBotMessage) = clientApi.sendPrivateMessage(userId, data)
suspend fun OneBotGroupMessageEvent.reply(message: String) = clientApi.sendGroupMessage(userId, message)
suspend fun OneBotGroupMessageEvent.reply(data: RawOneBotMessage) = clientApi.sendGroupMessage(userId, data)

suspend fun OneBotPrivateMessageEvent.replyAsync(message: String) = clientApi.sendPrivateMessageAsync(userId, message)
suspend fun OneBotPrivateMessageEvent.replyAsync(data: RawOneBotMessage) = clientApi.sendPrivateMessageAsync(userId, data)
suspend fun OneBotGroupMessageEvent.replyAsync(message: String) = clientApi.sendGroupMessageAsync(userId, message)
suspend fun OneBotGroupMessageEvent.replyAsync(data: RawOneBotMessage) = clientApi.sendGroupMessageAsync(userId, data)

/**
 * 如果此消息回复了其它消息
 * 可以获取被回复消息的详细信息
 */
suspend fun OneBotMessageEvent.getReplyMessage() = message.replyMessageId?.let { id -> clientApi.getMessage(id) }

/**
 * 撤回消息 仅自身为管理员时可执行
 */
suspend fun OneBotGroupMessageEvent.revoke() = clientApi.revokeMessage(messageId)

/**
 * 是否回复或提及到自身
 */
suspend fun OneBotGroupMessageEvent.hasMentionOrReplySelf() = message.hasMention(selfId) || getReplyMessage()?.sender?.userId == selfId