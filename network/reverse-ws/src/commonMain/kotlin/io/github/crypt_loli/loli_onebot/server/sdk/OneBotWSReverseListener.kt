package io.github.crypt_loli.loli_onebot.server.sdk

import io.github.crypt_loli.loli_onebot.sdk.OneBotListener
import io.github.crypt_loli.loli_onebot.server.module.ReverseWSClient

interface OneBotWSReverseListener: OneBotListener {
    fun onClientOnline(client: ReverseWSClient) { }
    fun onClientOffline(client: ReverseWSClient) { }
}