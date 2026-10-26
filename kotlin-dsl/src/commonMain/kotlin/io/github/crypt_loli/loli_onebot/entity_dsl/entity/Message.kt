package io.github.crypt_loli.loli_onebot.entity_dsl.entity

import io.github.crypt_loli.loli_onebot.entity.array.OneBotMessage
import io.github.crypt_loli.loli_onebot.entity.array.RawOneBotMessage
import io.github.crypt_loli.loli_onebot.entity.array.defaultFactor
import io.github.crypt_loli.loli_onebot.entity.array.type.ReplyEntity
import kotlin.jvm.JvmName

val RawOneBotMessage.format
    get() = defaultFactor.format(this)

val OneBotMessage.unformat
    get() = defaultFactor.unformat(this)

@get:JvmName("replyMessageIdRaw")
val RawOneBotMessage.replyMessageId
    get() = format.replyMessageId

@get:JvmName("replyMessageIdFormated")
val OneBotMessage.replyMessageId
    get() = filterIsInstance<ReplyEntity>().singleOrNull()?.id