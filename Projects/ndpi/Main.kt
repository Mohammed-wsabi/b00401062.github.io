package ndpi

import java.io.RandomAccessFile

object Main {
    @JvmStatic
    @ExperimentalUnsignedTypes
    fun deidentify(fp: RandomAccessFile, imageEntry: Utils.ImageEntry) {
        var buffer = Reader.readImageEntry2Buffer(fp, imageEntry)
        val image = ImgUtils.buffer2image(buffer)
        val roi = ImgUtils.Rectangle(0, 0, image.height, image.height)
        ImgUtils.mask(image, roi)
        buffer = ImgUtils.image2buffer(image)
        fp.seek((imageEntry.offset!!.values[0] as UInt).toLong())
        fp.write(buffer)
        fp.seek(imageEntry.size!!.pointer + 8)
        Writer.writeValue(fp, buffer.size)
    }

    @JvmStatic
    @ExperimentalUnsignedTypes
    fun main(args: Array<String>) {
        val path = args[0]
        val fp = RandomAccessFile(path, "rw")
        val imageEntries = Reader.readImageEntries(fp)
        val target = 6
        deidentify(fp, imageEntries[target])
        fp.close()
    }
}
