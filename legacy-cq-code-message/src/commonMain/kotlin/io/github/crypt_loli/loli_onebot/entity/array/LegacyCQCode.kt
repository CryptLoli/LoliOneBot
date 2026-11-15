package io.github.crypt_loli.loli_onebot.entity.array

import kotlinx.serialization.json.*

object LegacyCQCode {

    private const val TEXT_TYPE = "text"
    private const val TEXT_KEY = "text"
    const val CQ_PREFIX = "[CQ:"
    const val CQ_SUFFIX = "]"
    const val CQ_SPLIT = ','
    const val CQ_KV = "="

    fun encode(message: RawOneBotMessage): String = buildString {
        message.forEach { appendSegment(it.type, it.data) }
    }

    fun decode(message: String): RawOneBotMessage {
        val result = mutableListOf<RawArrayMessage>()
        var text = StringBuilder()
        var index = 0

        fun flushText() {
            if (text.isNotEmpty()) {
                result += RawArrayMessage(
                    TEXT_TYPE,
                    buildJsonObject { put(TEXT_KEY, text.toString()) }
                )
                text = StringBuilder()
            }
        }

        while (index < message.length) {
            val start = message.indexOf(CQ_PREFIX, index)
            if (start < 0) {
                text.append(message.substring(index).unescapeCQValue())
                break
            }

            text.append(message.substring(index, start).unescapeCQValue())
            val end = message.indexOf(CQ_SUFFIX, start + CQ_PREFIX.length)
            if (end < 0) {
                text.append(message.substring(start).unescapeCQValue())
                break
            }

            val code = message.substring(start + CQ_PREFIX.length, end)
            val fields = code.split(CQ_SPLIT)
            val type = fields.firstOrNull().orEmpty()
            if (type.isEmpty()) {
                text.append(message.substring(start, end + 1).unescapeCQValue())
            } else {
                flushText()
                val data = buildJsonObject {
                    fields.drop(1).forEach { field ->
                        val separator = field.indexOf(CQ_KV)
                        if (separator > 0) {
                            put(
                                field.substring(0, separator),
                                field.substring(separator + 1).unescapeCQValue()
                            )
                        }
                    }
                }
                result += RawArrayMessage(type, data)
            }
            index = end + 1
        }
        flushText()
        return result
    }

    private fun StringBuilder.appendSegment(type: String, data: JsonElement) {
        if (type == TEXT_TYPE) {
            append((data as? JsonObject)?.get(TEXT_KEY)?.jsonPrimitive?.content?.escapeText() ?: "")
            return
        }

        append(CQ_PREFIX).append(type)
        if (data is JsonObject) {
            data.forEach { (key, value) ->
                append(CQ_SPLIT).append(key).append(CQ_KV).append(value.asCQValue())
            }
        }
        append(CQ_SUFFIX)
    }

    private fun JsonElement.asCQValue(): String =
        if (this is JsonObject) escapeParameter(toString()) else escapeParameter(jsonPrimitive.content)

    private fun String.escapeText(): String = escapeCQValue(this, escapeComma = false)

    private fun escapeParameter(value: String): String = escapeCQValue(value, escapeComma = true)

    private fun escapeCQValue(value: String, escapeComma: Boolean): String = value
        .replace("&", "&amp;")
        .replace("[", "&#91;")
        .replace("]", "&#93;")
        .let { if (escapeComma) it.replace(",", "&#44;") else it }

    private fun String.unescapeCQValue(): String = replace("&#44;", ",")
        .replace("&#91;", "[")
        .replace("&#93;", "]")
        .replace("&amp;", "&")
}
