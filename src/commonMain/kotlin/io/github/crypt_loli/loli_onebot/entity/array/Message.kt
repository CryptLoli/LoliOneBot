package io.github.crypt_loli.loli_onebot.entity.array


typealias OneBotMessage = List<ArrayMessage>

interface Message {
    val message: List<ArrayMessage>
    val rawMessage: String
}