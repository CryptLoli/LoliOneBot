package io.github.crypt_loli.loli_onebot.entity.array

import kotlinx.serialization.Transient
import kotlinx.serialization.json.JsonElement

abstract class ArrayEntity {
    @Transient
    open var type: String = ""
    abstract fun encode(): JsonElement
}