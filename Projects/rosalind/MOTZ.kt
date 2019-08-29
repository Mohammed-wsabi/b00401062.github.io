import java.io.*
import java.util.*

object MOTZ {
    private val CACHE = object : HashMap<String, Integer>() {
        init {
            put("", 1)
            put("A", 1)
            put("C", 1)
            put("G", 1)
            put("U", 1)
            put("AA", 1)
            put("AC", 1)
            put("AG", 1)
            put("AU", 2)
            put("CA", 1)
            put("CC", 1)
            put("CG", 2)
            put("CU", 1)
            put("GA", 1)
            put("GC", 2)
            put("GG", 1)
            put("GU", 1)
            put("UA", 2)
            put("UC", 1)
            put("UG", 1)
            put("UU", 1)
        }
    }

    private fun motzkin(read: String): Int {
        if (CACHE.containsKey(read))
            return CACHE.get(read)
        var motzkin = motzkin(read.substring(1))
        for (i in 1 until read.length()) {
            if (CACHE.get(String.format("%c%c", read.charAt(0), read.charAt(i))) === 1)
                continue
            val head = read.substring(1, i)
            val tail = read.substring(i + 1)
            if (!CACHE.containsKey(head))
                CACHE.put(head, motzkin(head))
            if (!CACHE.containsKey(tail))
                CACHE.put(tail, motzkin(tail))
            motzkin = (CACHE.get(head) as Long * CACHE.get(tail) + motzkin) % 1000000
        }
        return motzkin
    }

    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        stdin.next()
        var read = ""
        while (stdin.hasNext())
            read += stdin.next()
        System.out.println(motzkin(read))
        stdin.close()
    }
}
