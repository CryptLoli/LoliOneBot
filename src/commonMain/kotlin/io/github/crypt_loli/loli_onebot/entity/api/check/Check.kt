package io.github.crypt_loli.loli_onebot.entity.api.check

import io.github.crypt_loli.loli_onebot.entity.api.ApiBase
import kotlinx.serialization.Serializable

@Serializable
class ApiCheckCanSendImageApi: ApiBase("can_send_image")

@Serializable
class ApiCheckCanSendRecordApi: ApiBase("can_send_record")