package ndpi

import java.io.RandomAccessFile
import java.nio.ByteBuffer

object Reader {
    @JvmStatic
    @ExperimentalUnsignedTypes
    @Throws(RuntimeException::class)
    inline fun <reified T> readValue(fd: RandomAccessFile): T {
        val size = NDPI.CLASS2SIZE.getValue(T::class)
        val array = ByteArray(size)
        fd.read(array)
        array.reverse()
        val buffer = ByteBuffer.wrap(array)
        return when (T::class) {
            Char::class -> buffer.get().toChar() as T
            UByte::class -> buffer.get().toUByte() as T
            UShort::class -> buffer.short.toUShort() as T
            UInt::class -> buffer.int.toUInt() as T
            Byte::class -> buffer.get() as T
            Short::class -> buffer.short as T
            Int::class -> buffer.int as T
            Float::class -> buffer.float as T
            Double::class -> buffer.double as T
            else -> throw RuntimeException()
        }
    }

    @JvmStatic
    @ExperimentalUnsignedTypes
    fun readValues(fd: RandomAccessFile, type: Int, count: Int) = when (type) {
        1 -> Array(count) { fd.readByte().toUByte() }
        2 -> Array(count) { readValue<Char>(fd) }
        3 -> Array(count) { readValue<UShort>(fd) }
        4 -> Array(count) { readValue<UInt>(fd) }
        5 -> Array(count) { readValue<UInt>(fd) to readValue<UInt>(fd) }
        6 -> Array(count) { fd.readByte() }
        8 -> Array(count) { readValue<Short>(fd) }
        9 -> Array(count) { readValue<Int>(fd) }
        10 -> Array(count) { readValue<Int>(fd) to readValue<Int>(fd) }
        11 -> Array(count) { readValue<Float>(fd) }
        12 -> Array(count) { readValue<Double>(fd) }
        else -> throw RuntimeException()
    }

    @JvmStatic
    @ExperimentalUnsignedTypes
    fun readEntries(fd: RandomAccessFile): List<NDPI.ImageEntry> {
        val imageEntries = mutableListOf<NDPI.ImageEntry>()
        assert(fd.readByte().toChar() == 'I')
        assert(fd.readByte().toChar() == 'I')
        assert(fd.readByte().toChar() == '*')
        assert(fd.readByte().toChar() == 0.toChar())
        var pointer: Long = readValue<UInt>(fd).toLong()
        var imageEntry: NDPI.ImageEntry? = null
        do {
            fd.seek(pointer)
            val entryNum = readValue<UShort>(fd).toInt()
            pointer += UShort.SIZE_BYTES
            repeat(entryNum) {
                val tag = readValue<UShort>(fd).toInt()
                val type = readValue<UShort>(fd).toInt()
                val count = readValue<UInt>(fd).toInt()
                if (count * NDPI.TYPE2SIZE[type] > 4)
                    fd.seek(readValue<UInt>(fd).toLong())
                val values = readValues(fd, type, count)
                if (tag == 273)
                    imageEntry = NDPI.ImageEntry(NDPI.Entry(pointer, tag, values))
                else if (tag == 279) {
                    imageEntry!!.size = NDPI.Entry(pointer, tag, values)
                    imageEntries.add(imageEntry!!)
                    imageEntry = null
                }
                pointer += 12
                fd.seek(pointer)
            }
            pointer = readValue<UInt>(fd).toLong()
        } while (pointer != 0L)
        return imageEntries
    }

    @JvmStatic
    @ExperimentalUnsignedTypes
    fun readImageEntry2Buffer(fd: RandomAccessFile, imageEntry: NDPI.ImageEntry): ByteArray {
        val buffer = ByteArray((imageEntry.size!!.values[0] as UInt).toInt())
        fd.seek((imageEntry.offset!!.values[0] as UInt).toLong())
        fd.read(buffer)
        return buffer
    }
}
