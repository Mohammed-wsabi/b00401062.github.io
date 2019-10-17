package ndpi

import java.io.RandomAccessFile

object Main {
    @JvmStatic
    @ExperimentalUnsignedTypes
    fun deidentify(fd: RandomAccessFile, imageEntry: NDPI.ImageEntry) {
        var buffer = Reader.readImageEntry2Buffer(fd, imageEntry)
        val image = Utils.buffer2image(buffer)
        val roi = Utils.Rectangle(0, 0, image.width, image.height)
        Utils.mask(image, roi)
        buffer = Utils.image2buffer(image)
        fd.seek((imageEntry.offset!!.values[0] as UInt).toLong())
        fd.write(buffer)
        fd.seek(imageEntry.size!!.pointer + 8)
        Writer.writeValue(fd, buffer.size)
    }

    @JvmStatic
    @ExperimentalUnsignedTypes
    fun main(args: Array<String>) {
        val path = args[0]
        val fd = RandomAccessFile(path, "rw")
        val images = Reader.readEntries(fd)
        val target = 6
        deidentify(fd, images[target])
        fd.close()
    }
}
