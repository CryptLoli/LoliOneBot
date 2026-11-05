package io.github.crypt_loli.loli_onebot.entity.ws

@Suppress("ConstPropertyName")
object StatusCode {
    const val OFFSET = 1000

    const val Ok = 0
    const val BadRequest = OFFSET + 400
    const val NotFound = OFFSET + 404
    const val InternalServerError = OFFSET + 500
}