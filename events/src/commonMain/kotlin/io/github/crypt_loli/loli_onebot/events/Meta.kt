package io.github.crypt_loli.loli_onebot.events

import io.github.crypt_loli.loli_onebot.entity.event.OneBotEvent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
abstract class OneBotMetaEvent : OneBotEvent()

/**
 * 心跳事件
 *
 * 由实现端定时推送给客户端, 用于告知实现端仍在正常运行
 *
 * @param interval 心跳间隔, 单位毫秒
 * @param status 实现端当前状态
 */
@Serializable
data class OneBotHeartbeatEvent(
    val interval: Long,
    val status: Status = Status()
) : OneBotMetaEvent() {

    /**
     * @param appEnabled 应用是否已启用
     * @param appGood 应用是否运行良好
     * @param appInitialized 应用是否已初始化
     * @param online 是否在线
     * @param good 是否运行良好
     */
    @Serializable
    data class Status(
        @SerialName("app_enabled")
        val appEnabled: Boolean = true,
        @SerialName("app_good")
        val appGood: Boolean = true,
        @SerialName("app_initialized")
        val appInitialized: Boolean = true,
        val online: Boolean = true,
        val good: Boolean = true
    ) {
        constructor(status: Boolean): this(online = status, good = status)
    }
}