package io.github.crypt_loli.loli_onebot.entity.array

import io.github.crypt_loli.loli_onebot.events.OneBotMessageEvent

fun RawOneBotMessage.encodeToCQCode(): String = LegacyCQCode.encode(this)
fun String.cqCodeDecodeToRawOneBotMessage(): RawOneBotMessage = LegacyCQCode.decode(this)

fun OneBotMessageEvent.fixRawMessageFromArrayMessage() = apply {
    rawMessage = message.encodeToCQCode()
}

fun OneBotMessageEvent.fixArrayMessageFromRawMessage() = apply {
    message = rawMessage.cqCodeDecodeToRawOneBotMessage()
}