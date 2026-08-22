package io.github.crypt_loli.loli_onebot.api_dsl

import io.github.crypt_loli.loli_onebot.OneBotApi
import io.github.crypt_loli.loli_onebot.entity.api.request.ApiRequestSetFriend
import io.github.crypt_loli.loli_onebot.entity.api.request.ApiRequestSetGroup

/**
 * 同意或拒绝好友申请
 *
 * @param flag 标记ID
 * @param approve 是否同意
 * @param remark 好友备注
 */
suspend fun OneBotApi.setFriendRequest(flag: String, approve: Boolean = true, remark: String = "") = send(
    entity = ApiRequestSetFriend(ApiRequestSetFriend.Params(flag, approve, remark))
)

/**
 * 同意或拒绝群申请
 *
 * @param flag 标记ID
 * @param type 类型 'invite'=被邀请入群, 'add'=加群请求
 * @param approve 是否同意
 * @param reason 拒绝理由
 */
suspend fun OneBotApi.setGroupRequest(flag: String, type: String, approve: Boolean = true, reason: String? = null) = send(
    entity = ApiRequestSetGroup(ApiRequestSetGroup.Params(flag, type, approve, reason))
)
