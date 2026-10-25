package io.github.crypt_loli.loli_onebot.entity.array


typealias RawOneBotMessage = List<RawArrayMessage>

interface Message {
    val message: List<RawArrayMessage>
    val rawMessage: String
}