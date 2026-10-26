package io.github.crypt_loli.loli_onebot.entity_dsl.entity

import io.github.crypt_loli.loli_onebot.entity.array.RawOneBotMessage
import io.github.crypt_loli.loli_onebot.entity.array.defaultFactor

val RawOneBotMessage.format
    get() = defaultFactor.format(this)

val RawOneBotMessage.replyMessageId
    get() = format.replyMessageId

fun RawOneBotMessage.hasMention(id: Long) = format.hasMention(id)