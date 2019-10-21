package ndpi

import java.io.RandomAccessFile

object Main {
    @JvmStatic
    @ExperimentalUnsignedTypes
    fun main(args: Array<String>) {
        val path = args[0]
        val fp = RandomAccessFile(path, "rw")
        val imageEntries = Reader.readImageEntries(fp)
        val target = 6
        Utils.deidentify(fp, imageEntries[target])
        fp.close()
    }
}
