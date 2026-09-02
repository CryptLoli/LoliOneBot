package io.github.crypt_loli.loli_onebot.entity.api

import kotlinx.serialization.Serializable

@Serializable
abstract class ApiBase(
    val action: String
)