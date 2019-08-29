import java.io.*
import java.util.*

object GC {
    private fun ratio(seq: String): Double {
        return seq.chars().filter({ x -> x === 'C' || x === 'G' }).count() as Double / seq.length()
    }

    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val ratios = HashMap()
        var name: String? = null
        var seq: String? = null
        while (true) {
            if (!stdin.hasNext()) {
                ratios.put(name, ratio(seq!!))
                break
            }
            val line = stdin.next()
            if (line.charAt(0) === '>') {
                if (name != null)
                    ratios.put(name, ratio(seq!!))
                name = line.substring(1)
                seq = ""
            } else
                seq += line
        }
        var ratio = 0.0
        for (key in ratios.keySet()) {
            if (ratios.get(key) > ratio) {
                name = key
                ratio = ratios.get(key)
            }
        }
        System.out.println(name)
        System.out.println(ratio * 100)
        stdin.close()
    }
}
