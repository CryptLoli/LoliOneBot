package io.github.crypt_loli.loli_onebot.server.sdk

import io.github.crypt_loli.loli_onebot.sdk.OneBotListener
import io.github.crypt_loli.loli_onebot.server.module.ReverseWSConnection

interface OneBotWSReverseListener: OneBotListener {
    fun onClientOnline(client: ReverseWSConnection) { }
    fun onClientOffline(client: ReverseWSConnection) { }
}