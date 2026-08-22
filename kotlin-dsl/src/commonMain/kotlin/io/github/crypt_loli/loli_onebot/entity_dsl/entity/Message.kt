package io.github.crypt_loli.loli_onebot.entity_dsl.entity

import io.github.crypt_loli.loli_onebot.entity.array.OneBotMessage
import io.github.crypt_loli.loli_onebot.entity.array.SegmentType

val OneBotMessage.replyMessageId
    get() = firstOrNull { it.type == SegmentType.Reply }?.data?.id?.toLong()