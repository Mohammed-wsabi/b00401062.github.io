import java.io.*
import java.util.*

object CAT {
    private val CACHE = object : HashMap<String, Integer>() {
        init {
            put("", 1)
            put("AA", 0)
            put("AC", 0)
            put("AG", 0)
            put("AU", 1)
            put("CA", 0)
            put("CC", 0)
            put("CG", 1)
            put("CU", 0)
            put("GA", 0)
            put("GC", 1)
            put("GG", 0)
            put("GU", 0)
            put("UA", 1)
            put("UC", 0)
            put("UG", 0)
            put("UU", 0)
        }
    }

    private fun catalan(read: String): Int {
        var catalan = 0
        if (CACHE.containsKey(read))
            return CACHE.get(read)
        for (i in 0 until read.length() / 2) {
            if (CACHE.get(String.format("%c%c", read.charAt(0), read.charAt(2 * i + 1))) === 0)
                continue
            val head = read.substring(1, 2 * i + 1)
            val tail = read.substring(2 * i + 2)
            if (!CACHE.containsKey(head))
                CACHE.put(head, catalan(head))
            if (!CACHE.containsKey(tail))
                CACHE.put(tail, catalan(tail))
            catalan = (CACHE.get(head) as Long * CACHE.get(tail) + catalan) % 1000000
        }
        return catalan
    }

    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        stdin.next()
        var read = ""
        while (stdin.hasNext())
            read += stdin.next()
        System.out.println(catalan(read))
        stdin.close()
    }
}
