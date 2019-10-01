package ndpi

import java.io.RandomAccessFile

@ExperimentalUnsignedTypes
fun deidentify(fd: RandomAccessFile, imageEntry: ImageEntry) {
    var buffer: ByteArray
    buffer = readImageEntry2Buffer(fd, imageEntry)
    val image = buffer2image(buffer)
    val roi = Rectangle(0, 0, image.width, image.height)
    mask(image, roi)
    buffer = image2buffer(image)
    fd.seek((imageEntry.offset!!.values[0] as UInt).toLong())
    fd.write(buffer)
    fd.seek(imageEntry.size!!.pointer + 8)
    writeValue(fd, buffer.size)
}

@ExperimentalUnsignedTypes
fun main(args: Array<String>) {
    val path = args[0]
    val fd = RandomAccessFile(path, "rw")
    val images = readEntries(fd)
    val target = 6
    deidentify(fd, images[target])
    fd.close()
}
