package io.github.crypt_loli.loli_onebot.entity.array

import kotlinx.serialization.KSerializer

/**
 * @param type 类型名称
 * @param single 用于有效性检查, 是否`单一实例`, 即同列表里`至多一个该类实例`
 * @param exactly 用于有效性检查, 是否`唯一实例`, 如果是则列表出现此类实例后不能再同时出现其它任何实例
 */
class ArrayEntityFactor<T>(
    val type: String,
    val serializer: KSerializer<T>,
    val single: Boolean = false,
    val exactly: Boolean = false
)