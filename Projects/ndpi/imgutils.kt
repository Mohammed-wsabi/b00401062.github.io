package ndpi

import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO

data class Rectangle(val x: Int, val y: Int, val w: Int, val h: Int)

fun buffer2image(buffer: ByteArray): BufferedImage {
    val stream = ByteArrayInputStream(buffer)
    return ImageIO.read(stream)
}

fun image2buffer(image: BufferedImage): ByteArray {
    val stream = ByteArrayOutputStream()
    ImageIO.write(image, "jpg", stream)
    return stream.toByteArray()
}

fun mask(image: BufferedImage, roi: Rectangle) {
    val mask = Array(image.width) { 0 }.toIntArray()
    image.setRGB(roi.x, roi.y, roi.w, roi.h, mask, 0, 0)
}
