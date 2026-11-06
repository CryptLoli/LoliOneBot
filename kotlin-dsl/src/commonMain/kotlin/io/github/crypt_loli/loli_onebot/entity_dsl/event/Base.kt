package io.github.crypt_loli.loli_onebot.entity_dsl.event

import io.github.crypt_loli.loli_onebot.OneBotClientApi
import io.github.crypt_loli.loli_onebot.entity.event.OneBotEvent

val OneBotEvent.clientApi
    get() = this.api as OneBotClientApi