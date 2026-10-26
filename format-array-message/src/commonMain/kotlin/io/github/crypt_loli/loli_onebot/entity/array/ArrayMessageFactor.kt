package io.github.crypt_loli.loli_onebot.entity.array

import io.github.crypt_loli.loli_onebot.entity.array.type.*
import io.github.crypt_loli.loli_onebot.utils.jsonReceive

class ArrayMessageFactor {
    val types = hashMapOf<String, ArrayEntityFactor<out ArrayEntity>>()

    init {
        addType(ContactEntity.factor)
        addType(DiceEntity.factor)
        addType(FaceEntity.factor)
        addType(ImageEntity.factor)
        addType(JsonEntity.factor)
        addType(LocationEntity.factor)
        addType(MentionEntity.factor)
        addType(RecordEntity.factor)
        addType(ReplyEntity.factor)
        addType(RpsEntity.factor)
        addType(ShareEntity.factor)
        addType(TextEntity.factor)
        addType(VideoEntity.factor)
        addType(XMLEntity.factor)
    }

    fun addType(type: ArrayEntityFactor<out ArrayEntity>) {
        types[type.type] = type
    }

    fun format(data: List<RawArrayMessage>) = data
        .map { entity ->
            val type = types[entity.type] ?: return@map UnknownEntity(entity.type, entity.data)

            runCatching {
                jsonReceive.decodeFromJsonElement(type.serializer, entity.data)
                    .apply { this.baseType = entity.type }
            }
                .fold(
                    onSuccess = { it },
                    onFailure = { UnknownEntity(entity.type, entity.data) }
                )
        }
        .toMutableList()

    fun unformat(data: List<ArrayEntity>) = data
        .map { entity -> RawArrayMessage(entity.baseType, entity.encode()) }

}