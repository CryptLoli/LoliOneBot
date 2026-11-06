package io.github.crypt_loli.loli_onebot.utils.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.time.Instant

object InstantLongSSerializer: KSerializer<Instant> {

    override val descriptor = PrimitiveSerialDescriptor("InstantLongS", PrimitiveKind.LONG)

    override fun serialize(encoder: Encoder, value: Instant) {
        encoder.encodeLong(value.toEpochMilliseconds() / 1000L)
    }

    override fun deserialize(decoder: Decoder) = Instant.fromEpochMilliseconds(decoder.decodeLong() * 1000L)
}