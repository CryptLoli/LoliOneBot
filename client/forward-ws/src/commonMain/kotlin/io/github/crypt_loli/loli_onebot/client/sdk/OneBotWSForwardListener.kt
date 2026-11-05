package io.github.crypt_loli.loli_onebot.client.sdk

import io.github.crypt_loli.loli_onebot.sdk.OneBotListener

interface OneBotWSForwardListener: OneBotListener {
    fun onConnect() { }
    fun onDisconnect(reason: Throwable? = null) { }
}
