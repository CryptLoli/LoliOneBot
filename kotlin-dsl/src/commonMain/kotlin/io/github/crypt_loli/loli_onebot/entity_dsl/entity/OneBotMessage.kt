package io.github.crypt_loli.loli_onebot.entity_dsl.entity

import io.github.crypt_loli.loli_onebot.entity.array.ArrayEntity
import io.github.crypt_loli.loli_onebot.entity.array.OneBotMessage
import io.github.crypt_loli.loli_onebot.entity.array.defaultFactor
import io.github.crypt_loli.loli_onebot.entity.array.type.MentionEntity
import io.github.crypt_loli.loli_onebot.entity.array.type.ReplyEntity

val OneBotMessage.unformat
    get() = defaultFactor.unformat(this)

var OneBotMessage.replyMessageId
    get() = filterIsInstance<ReplyEntity>().singleOrNull()?.id
    set(value) {
        removeAll(filterIsInstance<ReplyEntity>())
        value?.also { id -> add(0, ReplyEntity(id)) }
    }

/**
 * 检查是否有提及某个人
 */
fun OneBotMessage.hasMention(id: Long) = filterIsInstance<MentionEntity>().any { it.qq == id }

/**
 * 此消息提及了哪些人
 */
val OneBotMessage.mentionUsers
    get() = filterIsInstance<MentionEntity>().map { it.qq }

fun buildOneBotMessage(replyId: Long? = null, block: OneBotMessage.() -> Unit) = mutableListOf<ArrayEntity>()
    .apply { replyMessageId = replyId }
    .apply(block)