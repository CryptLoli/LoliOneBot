package io.github.crypt_loli.loli_onebot.entity.base

import kotlinx.serialization.SerialName

enum class UserSex {
    @SerialName("male")
    Male,
    @SerialName("female")
    Female,
    @SerialName("unknown")
    Unknown
}