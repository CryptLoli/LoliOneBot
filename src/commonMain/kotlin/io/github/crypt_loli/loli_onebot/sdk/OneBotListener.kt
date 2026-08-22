package io.github.crypt_loli.loli_onebot.sdk

import io.github.crypt_loli.loli_onebot.entity.event.*
import kotlinx.serialization.json.JsonObject

interface OneBotListener {
    suspend fun onRawMessage(raw: JsonObject) { }
    suspend fun onPrivateMessage(event: OneBotPrivateMessageEvent) { }
    suspend fun onGroupMessage(event: OneBotGroupMessageEvent) { }
    suspend fun onRequestBeAddFriend(event: OneBotRequestBeAddFriend) { }
    suspend fun onRequestBeInviteGroup(event: OneBotRequestBeInviteGroup) { }
    suspend fun onRequestJoinGroup(event: OneBotRequestJoinGroup) { }
    suspend fun onNoticePoke(event: OneBotNoticePokeEvent) { }
}