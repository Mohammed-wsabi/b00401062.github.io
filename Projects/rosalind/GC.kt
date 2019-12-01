package rosalind

import java.io.IOException
import java.util.*

object GC {
    private fun ratio(seq: String?): Double {
        return seq!!.chars().filter { x: Int -> x == 'C'.toInt() || x == 'G'.toInt() }.count().toDouble() / seq.length
    }

    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val ratios: MutableMap<String?, Double> = HashMap()
        var name: String? = null
        var seq: String? = null
        while (true) {
            if (!stdin.hasNext()) {
                ratios[name] = ratio(seq)
                break
            }
            val line = stdin.next()
            if (line[0] == '>') {
                if (name != null) ratios[name] = ratio(seq)
                name = line.substring(1)
                seq = ""
            } else seq += line
        }
        var ratio = 0.0
        for (key in ratios.keys) {
            if (ratios[key]!! > ratio) {
                name = key
                ratio = ratios[key]!!
            }
        }
        println(name)
        println(ratio * 100)
        stdin.close()
    }
}