package io.github.crypt_loli.loli_onebot.entity_dsl.event

import io.github.crypt_loli.loli_onebot.api_dsl.*
import io.github.crypt_loli.loli_onebot.entity.array.OneBotMessage
import io.github.crypt_loli.loli_onebot.entity.event.OneBotGroupMessageEvent
import io.github.crypt_loli.loli_onebot.entity.event.OneBotMessageEvent
import io.github.crypt_loli.loli_onebot.entity.event.OneBotPrivateMessageEvent
import io.github.crypt_loli.loli_onebot.entity_dsl.entity.replyMessageId

suspend fun OneBotPrivateMessageEvent.reply(message: String) = api.sendPrivateMessage(userId, message)
suspend fun OneBotPrivateMessageEvent.reply(data: OneBotMessage) = api.sendPrivateMessage(userId, data)
suspend fun OneBotGroupMessageEvent.reply(message: String) = api.sendGroupMessage(userId, message)
suspend fun OneBotGroupMessageEvent.reply(data: OneBotMessage) = api.sendGroupMessage(userId, data)

suspend fun OneBotPrivateMessageEvent.replyAsync(message: String) = api.sendPrivateMessageAsync(userId, message)
suspend fun OneBotPrivateMessageEvent.replyAsync(data: OneBotMessage) = api.sendPrivateMessageAsync(userId, data)
suspend fun OneBotGroupMessageEvent.replyAsync(message: String) = api.sendGroupMessageAsync(userId, message)
suspend fun OneBotGroupMessageEvent.replyAsync(data: OneBotMessage) = api.sendGroupMessageAsync(userId, data)

/**
 * 如果此消息回复了其它消息
 * 可以获取被回复消息的详细信息
 */
suspend fun OneBotMessageEvent.getReplyMessage() = message.replyMessageId?.let { id -> api.getMessage(id) }

/**
 * 撤回消息 仅自身为管理员时可执行
 */
suspend fun OneBotGroupMessageEvent.revoke() = api.revokeMessage(messageId)