package io.github.crypt_loli.loli_onebot.entity.event

import io.github.crypt_loli.loli_onebot.OneBotApi
import io.github.crypt_loli.loli_onebot.utils.serializer.InstantLongSSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlin.time.Instant

/**
 * @param selfId 自身账号
 * @param time 事件发出时间
 */
@Serializable
open class OneBotEvent(
    @Serializable(with = InstantLongSSerializer::class)
    open val time: Instant = Instant.DISTANT_PAST,
    @SerialName("self_id")
    open val selfId: Long = 0L,
    @SerialName("post_type")
    open val postType: OneBotPostType = OneBotPostType.Invalid
) {
    @Transient
    lateinit var api: OneBotApi
}

@Serializable
enum class OneBotPostType {
    @SerialName("notice")
    Notice,
    @SerialName("message")
    Message,
    @SerialName("request")
    Request,
    @SerialName("meta_event")
    MetaEvent,

    Invalid
}