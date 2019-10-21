package ndpi

import java.io.RandomAccessFile
import java.nio.ByteBuffer

object Writer {
    @JvmStatic
    @ExperimentalUnsignedTypes
    @Throws(RuntimeException::class)
    inline fun <reified T> writeValue(fp: RandomAccessFile, value: T) {
        val size = Utils.CLASS2SIZE.getValue(T::class)
        val buffer = ByteBuffer.allocate(size)
        when (T::class) {
            Char::class -> buffer.put((value as Char).toByte())
            UByte::class -> buffer.put((value as UByte).toByte())
            UShort::class -> buffer.putShort((value as UShort).toShort())
            UInt::class -> buffer.putInt((value as UInt).toInt())
            Byte::class -> buffer.put(value as Byte)
            Short::class -> buffer.putShort(value as Short)
            Int::class -> buffer.putInt(value as Int)
            Float::class -> buffer.putFloat(value as Float)
            Double::class -> buffer.putDouble(value as Double)
            else -> throw RuntimeException()
        }
        val array = buffer.array()
        array.reverse()
        fp.write(array)
    }
}
