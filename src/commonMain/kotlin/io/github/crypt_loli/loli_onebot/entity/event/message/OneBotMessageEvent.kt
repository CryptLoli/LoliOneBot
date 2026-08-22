package io.github.crypt_loli.loli_onebot.entity.event.message

import io.github.crypt_loli.loli_onebot.entity.event.OneBotEvent
import io.github.crypt_loli.loli_onebot.entity.array.ArrayMessage
import io.github.crypt_loli.loli_onebot.entity.base.MessageType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
open class OneBotMessageEvent(
    @SerialName("message_type")
    val messageType: MessageType = MessageType.Group,
    @SerialName("sub_type")
    val subType: SubType = SubType.Invalid,
    @SerialName("message_id")
    val messageId: Long = 0L,
    @SerialName("user_id")
    val userId: Long = 0L,
    val message: List<ArrayMessage> = emptyList(),
    @SerialName("raw_message")
    val rawMessage: String = "",
    val font: Int = 0,
    val sender: Sender = Sender()
): OneBotEvent() {

    @Serializable
    enum class SubType {
        /**
         * 好友
         */
        @SerialName("friend")
        Friend,
        /**
         * 群聊
         */
        @SerialName("normal")
        Normal,
        /**
         * 匿名
         */
        @SerialName("anonymous")
        @Deprecated("该功能已被QQ移除")
        Anonymous,
        /**
         * 群中自身发送
         */
        @SerialName("group_self")
        GroupSelf,
        /**
         * 群临时会话
         */
        @SerialName("group")
        Group,
        /**
         * 系统提示
         */
        @SerialName("notice")
        Notice,

        Invalid
    }


    /**
     * 发送者数据
     *
     * @param userId 发送者账号
     * @param nickname 发送者名称
     * @param sex 发送者性别
     * @param age 发送者年龄
     *
     * @param groupId 如果为临时会话 此处为来源群
     *
     * @param card 如果为群消息 此处为群昵称
     * @param area 如果为群消息 此处为地区
     * @param level 如果为群消息 此处为群等级
     * @param role 如果为群消息 此处为身份标签 (群主/管理员/成员)
     * @param title 如果为群消息 此处为专属头衔
     */
    @Serializable
    data class Sender(
        @SerialName("user_id")
        val userId: Long = 0L,
        val nickname: String = "",
        val sex: Sex = Sex.Unknown,
        val age: Int = 0,

        val groupId: Long? = null,

        val card: String? = null,
        val area: String? = null,
        val level: String? = null,
        val role: Role? = null,
        val title: String? = null
    ) {
        enum class Sex {
            @SerialName("male")
            Male,
            @SerialName("female")
            Female,
            @SerialName("unknown")
            Unknown
        }

        enum class Role {
            @SerialName("owner")
            Owner,
            @SerialName("admin")
            Admin,
            @SerialName("member")
            Member
        }

    }

}