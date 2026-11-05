package io.github.crypt_loli.loli_onebot.utils

import kotlinx.coroutines.channels.Channel

suspend inline fun <reified T> produceOne(block: suspend Channel<T>.() -> Unit) = Channel<T>()
    .apply {
        runCatching { block() }.onFailure { close(it) }
    }
    .receiveCatching()
    .getOrThrow()