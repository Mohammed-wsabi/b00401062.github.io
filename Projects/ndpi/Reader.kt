package ndpi

import java.io.RandomAccessFile
import java.nio.ByteBuffer

object Reader {
    @JvmStatic
    @ExperimentalUnsignedTypes
    @Throws(RuntimeException::class)
    inline fun <reified T> readValue(fp: RandomAccessFile): T {
        val size = Utils.CLASS2SIZE.getValue(T::class)
        val array = ByteArray(size)
        fp.read(array)
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
    fun readValues(fp: RandomAccessFile, type: Int, count: Int) = when (type) {
        1 -> Array(count) { fp.readByte().toUByte() }
        2 -> Array(count) { readValue<Char>(fp) }
        3 -> Array(count) { readValue<UShort>(fp) }
        4 -> Array(count) { readValue<UInt>(fp) }
        5 -> Array(count) { readValue<UInt>(fp) to readValue<UInt>(fp) }
        6 -> Array(count) { fp.readByte() }
        8 -> Array(count) { readValue<Short>(fp) }
        9 -> Array(count) { readValue<Int>(fp) }
        10 -> Array(count) { readValue<Int>(fp) to readValue<Int>(fp) }
        11 -> Array(count) { readValue<Float>(fp) }
        12 -> Array(count) { readValue<Double>(fp) }
        else -> throw RuntimeException()
    }

    @JvmStatic
    @ExperimentalUnsignedTypes
    fun readImageEntries(fp: RandomAccessFile): List<Utils.ImageEntry> {
        val imageEntries = mutableListOf<Utils.ImageEntry>()
        assert(fp.readByte().toChar() == 'I')
        assert(fp.readByte().toChar() == 'I')
        assert(fp.readByte().toChar() == '*')
        assert(fp.readByte().toChar() == 0.toChar())
        var pointer: Long = readValue<UInt>(fp).toLong()
        var imageEntry: Utils.ImageEntry? = null
        do {
            fp.seek(pointer)
            val entryNum = readValue<UShort>(fp).toInt()
            pointer += UShort.SIZE_BYTES
            repeat(entryNum) {
                val tag = readValue<UShort>(fp).toInt()
                val type = readValue<UShort>(fp).toInt()
                val count = readValue<UInt>(fp).toInt()
                if (count * Utils.TYPE2SIZE[type] > 4)
                    fp.seek(readValue<UInt>(fp).toLong())
                val values = readValues(fp, type, count)
                if (tag == 273)
                    imageEntry = Utils.ImageEntry(Utils.Entry(pointer, tag, values))
                else if (tag == 279) {
                    imageEntry!!.size = Utils.Entry(pointer, tag, values)
                    imageEntries.add(imageEntry!!)
                    imageEntry = null
                }
                pointer += 12
                fp.seek(pointer)
            }
            pointer = readValue<UInt>(fp).toLong()
        } while (pointer != 0L)
        return imageEntries
    }

    @JvmStatic
    @ExperimentalUnsignedTypes
    fun readImageBuffer(fp: RandomAccessFile, imageEntry: Utils.ImageEntry): ByteArray {
        val buffer = ByteArray((imageEntry.size!!.values[0] as UInt).toInt())
        fp.seek((imageEntry.offset!!.values[0] as UInt).toLong())
        fp.read(buffer)
        return buffer
    }
}
