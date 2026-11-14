package io.github.crypt_loli.loli_onebot.entity.array

fun RawOneBotMessage.encodeToCQCode(): String = LegacyCQCode.encode(this)
fun String.cqCodeDecodeToRawOneBotMessage(): RawOneBotMessage = LegacyCQCode.decode(this)
