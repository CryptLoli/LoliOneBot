package io.github.crypt_loli.loli_onebot.entity_dsl.entity

import io.github.crypt_loli.loli_onebot.entity.array.ArrayEntity
import io.github.crypt_loli.loli_onebot.entity.array.OneBotMessage
import io.github.crypt_loli.loli_onebot.entity.array.defaultFactor
import io.github.crypt_loli.loli_onebot.entity.array.type.MentionEntity
import io.github.crypt_loli.loli_onebot.entity.array.type.ReplyEntity

val OneBotMessage.unformat
    get() = defaultFactor.unformat(this)

val OneBotMessage.replyMessageId
    get() = filterIsInstance<ReplyEntity>().singleOrNull()?.id

fun OneBotMessage.hasMention(id: Long) = filterIsInstance<MentionEntity>().any { it.qq == id }

fun buildOneBotMessage(block: OneBotMessage.() -> Unit) = mutableListOf<ArrayEntity>().apply(block)