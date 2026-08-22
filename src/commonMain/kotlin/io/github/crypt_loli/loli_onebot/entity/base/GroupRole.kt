package io.github.crypt_loli.loli_onebot.entity.base

import kotlinx.serialization.SerialName

enum class GroupRole {
    @SerialName("owner")
    Owner,
    @SerialName("admin")
    Admin,
    @SerialName("member")
    Member
}