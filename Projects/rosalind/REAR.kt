package rosalind

import java.io.IOException
import java.util.*
import java.util.stream.Collectors

object REAR {
    private fun reversal(s: List<Int>, t: List<Int>): Int {
        val n = s.size
        val distance: MutableMap<List<Int?>, Int?> = mutableMapOf(s to 0)
        val queue = LinkedList<List<Int>>()
        queue.add(s)
        while (queue.size > 0 && !distance.containsKey(t)) {
            val u: List<Int> = ArrayList(queue.remove())
            val d = distance[u]!!
            for (i in 0 until n) for (j in i + 2..n) {
                Collections.reverse(u.subList(i, j))
                val v: List<Int> = ArrayList(u)
                Collections.reverse(u.subList(i, j))
                if (distance.containsKey(v)) continue
                distance[v] = d + 1
                queue.add(v)
                if (v == t) return distance[t]!!
            }
        }
        return distance[t]!!
    }

    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        do {
            val s = stdin.nextLine().split(" ").map(String::toInt)
            val t = stdin.nextLine().split(" ").map(String::toInt)
            println(reversal(s, t))
        } while (stdin.hasNext() && stdin.nextLine() == "")
        stdin.close()
    }
}