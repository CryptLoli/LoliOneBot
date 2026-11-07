package io.github.crypt_loli.loli_onebot.utils

import kotlinx.coroutines.CancellationException

suspend inline fun <R> safeRunCatching(block: suspend () -> R) = runCatching { block() }
    .onFailure { e -> if (e is CancellationException) throw e }