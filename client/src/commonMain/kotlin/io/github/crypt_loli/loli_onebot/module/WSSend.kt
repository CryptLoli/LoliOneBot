package io.github.crypt_loli.loli_onebot.module

interface WSSend {
    suspend fun send(text: String)
    suspend fun disconnect()
}