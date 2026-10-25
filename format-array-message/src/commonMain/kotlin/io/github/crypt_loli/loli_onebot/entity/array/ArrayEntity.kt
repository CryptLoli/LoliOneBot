package io.github.crypt_loli.loli_onebot.entity.array

import kotlinx.serialization.json.JsonElement

abstract class ArrayEntity {
    open var type: String = ""
    abstract fun encode(): JsonElement
}