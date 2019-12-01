package rosalind

import java.io.IOException
import java.util.*

object CONV {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val S1 = Arrays.stream(stdin.nextLine().split(" ".toRegex()).toTypedArray()).mapToDouble { s: String -> s.toDouble() }.toArray()
        val S2 = Arrays.stream(stdin.nextLine().split(" ".toRegex()).toTypedArray()).mapToDouble { s: String -> s.toDouble() }.toArray()
        val counter: MutableMap<String, Int> = HashMap()
        for (s1 in S1) for (s2 in S2) {
            val key = String.format("%.5f", s1 - s2)
            counter[key] = counter.getOrDefault(key, 0) + 1
        }
        counter.entries.stream().sorted(java.util.Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(1).forEach { e: Map.Entry<String, Int> ->
            println(e.value)
            println(e.key)
        }
        stdin.close()
    }
}