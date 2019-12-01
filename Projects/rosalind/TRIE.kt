package rosalind

import java.io.IOException
import java.util.*

object TRIE {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val root = Node()
        while (stdin.hasNextLine()) root.add(stdin.nextLine())
        for (e in root.edges()) System.out.printf("%d %d %c\n", e.s, e.t, e.c)
        stdin.close()
    }

    private class Edge internal constructor(var s: Int, var t: Int, var c: Char)

    private class Node {
        var c = arrayOfNulls<Node>(4)
        fun add(s: String) {
            if (s.length == 0) return
            val i = "ACGT".indexOf(s[0])
            if (c[i] == null) c[i] = Node()
            c[i]!!.add(s.substring(1))
        }

        fun edges(): List<Edge> {
            val q: Queue<Node?> = LinkedList()
            val e: MutableList<Edge> = ArrayList()
            var head = 0
            var tail = 0
            q.add(this)
            tail++
            do {
                val n = q.remove()
                for (i in 0..3) if (n!!.c[i] != null) {
                    q.add(n.c[i])
                    tail++
                    e.add(Edge(head + 1, tail, "ACGT"[i]))
                }
            } while (++head < tail)
            return e
        }
    }
}