package io.github.crypt_loli.loli_onebot.sdk

import io.github.crypt_loli.loli_onebot.entity.event.*

interface OneBotListener {
    suspend fun onPrivateMessage(event: OneBotPrivateMessageEvent) { }
    suspend fun onGroupMessage(event: OneBotGroupMessageEvent) { }
    suspend fun onRequestBeAddFriend(event: OneBotRequestBeAddFriend) { }
    suspend fun onRequestBeInviteGroup(event: OneBotRequestBeInviteGroup) { }
    suspend fun onRequestJoinGroup(event: OneBotRequestJoinGroup) { }
    suspend fun onNoticePoke(event: OneBotNoticePokeEvent) { }
}