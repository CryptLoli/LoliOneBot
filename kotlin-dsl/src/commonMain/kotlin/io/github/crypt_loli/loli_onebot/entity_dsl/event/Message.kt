package io.github.crypt_loli.loli_onebot.entity_dsl.event

import io.github.crypt_loli.loli_onebot.api_dsl.revokeMessage
import io.github.crypt_loli.loli_onebot.api_dsl.sendGroupMessage
import io.github.crypt_loli.loli_onebot.api_dsl.sendGroupMessageAsync
import io.github.crypt_loli.loli_onebot.api_dsl.sendPrivateMessage
import io.github.crypt_loli.loli_onebot.api_dsl.sendPrivateMessageAsync
import io.github.crypt_loli.loli_onebot.entity.array.ArrayMessage
import io.github.crypt_loli.loli_onebot.entity.event.message.OneBotGroupMessageEvent
import io.github.crypt_loli.loli_onebot.entity.event.message.OneBotPrivateMessageEvent

suspend fun OneBotPrivateMessageEvent.reply(message: String) = api.sendPrivateMessage(userId, message)
suspend fun OneBotPrivateMessageEvent.reply(data: List<ArrayMessage>) = api.sendPrivateMessage(userId, data)
suspend fun OneBotGroupMessageEvent.reply(message: String) = api.sendGroupMessage(userId, message)
suspend fun OneBotGroupMessageEvent.reply(data: List<ArrayMessage>) = api.sendGroupMessage(userId, data)

suspend fun OneBotPrivateMessageEvent.replyAsync(message: String) = api.sendPrivateMessageAsync(userId, message)
suspend fun OneBotPrivateMessageEvent.replyAsync(data: List<ArrayMessage>) = api.sendPrivateMessageAsync(userId, data)
suspend fun OneBotGroupMessageEvent.replyAsync(message: String) = api.sendGroupMessageAsync(userId, message)
suspend fun OneBotGroupMessageEvent.replyAsync(data: List<ArrayMessage>) = api.sendGroupMessageAsync(userId, data)

/**
 * 撤回消息 仅自身为管理员时可执行
 */
suspend fun OneBotGroupMessageEvent.revoke() = api.revokeMessage(messageId)