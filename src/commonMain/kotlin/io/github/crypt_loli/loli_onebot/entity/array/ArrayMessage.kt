package io.github.crypt_loli.loli_onebot.entity.array

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class ArrayMessage(
    val type: SegmentType,
    val data: Data,
) {
    @Serializable
    data class Data(
        /**
         * json/xml
         */
        val data: String? = null,
        /**
         * ID
         */
        val id: String? = null,
        /**
         * qq号
         */
        val qq: String? = null,
        /**
         * 纯文本消息的内容
         */
        val text: String? = null,
        /**
         * 文件id
         */
        val file: String? = null,
        /**
         * 文件名
         */
        val filename: String? = null,
        /**
         * 文件/图片/语音的url
         */
        val url: String? = null,
        /**
         * 大纲
         */
        val summary: String? = null,
        /**
         * 子类型/内部使用
         */
        val subType: String? = null,
        /**
         * 名称/通用
         */
        val name: String? = null,
        val type: String? = null,
        /**
         * 标题/通用
         */
        val title: String? = null,
        /**
         * 纬度
         */
        val lat: Double? = null,
        /**
         * 经度
         */
        val lon: Double? = null,
        /**
         * 内容/通用
         */
        val content: JsonElement? = null,
        /**
         * 音频链接
         */
        val audio: String? = null,
        /**
         * emoji的id
         */
        @SerialName("emoji_id")
        val emojiId: String? = null,
        /**
         * emoji的包id
         */
        @SerialName("emoji_package_id")
        val emojiPackageId: String? = null,
        /**
         *
         */
        val key: String? = null,
        /**
         * 图片是否为large
         */
        val large: String? = null,
        /**
         * 文件名
         */
        @SerialName("file_name")
        val fileName: String? = null,
        /**
         * 文件hash
         */
        @SerialName("file_hash")
        val fileHash: String? = null,
        /**
         * 文件ID
         */
        @SerialName("file_id")
        val fileId: String? = null,
    )
}