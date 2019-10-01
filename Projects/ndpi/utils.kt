package ndpi

import kotlin.reflect.KClass

@ExperimentalUnsignedTypes
class Entry(val pointer: Long, val tag: Int, val values: Array<out Any>) {
    override fun toString() = "$tag@$pointer: " + when {
        values.isArrayOf<Char>() -> values.joinToString("").trim()
        else -> values.contentToString()
    }
}

@ExperimentalUnsignedTypes
data class ImageEntry(var offset: Entry? = null, var size: Entry? = null)

val Float.Companion.SIZE_BYTES get() = 4
val Double.Companion.SIZE_BYTES get() = 8

@ExperimentalUnsignedTypes
val TYPE2SIZE = arrayOf(
    0, // UNDEFINED
    UByte.SIZE_BYTES, // BYTE
    Char.SIZE_BYTES / 2, // ASCII
    UShort.SIZE_BYTES, // SHORT
    UInt.SIZE_BYTES, // LONG
    UInt.SIZE_BYTES * 2, // RATIONAL
    Byte.SIZE_BYTES, // SBYTE
    0, // UNDEFINED
    Short.SIZE_BYTES, // SSHORT
    Int.SIZE_BYTES, // SLONG
    Int.SIZE_BYTES * 2, // SRATIONAL
    Float.SIZE_BYTES, // FLOAT
    Double.SIZE_BYTES // DOUBLE
)

@ExperimentalUnsignedTypes
val CLASS2SIZE: Map<KClass<*>, Int> = mapOf(
    Char::class to Char.SIZE_BYTES / 2,
    UByte::class to UByte.SIZE_BYTES,
    UShort::class to UShort.SIZE_BYTES,
    UInt::class to UInt.SIZE_BYTES,
    Byte::class to Byte.SIZE_BYTES,
    Short::class to Short.SIZE_BYTES,
    Int::class to Int.SIZE_BYTES,
    Float::class to Float.SIZE_BYTES,
    Double::class to Double.SIZE_BYTES
)
