import java.io.*
import java.util.*

object TRIE {
    private class Edge internal constructor(internal var s: Int, internal var t: Int, internal var c: Char)
    private class Node {
        internal var c = arrayOfNulls<Node>(4)
        internal fun add(s: String) {
            if (s.length() === 0) return
            val i = "ACGT".indexOf(s.charAt(0))
            if (c[i] == null)
                c[i] = Node()
            c[i].add(s.substring(1))
        }

        internal fun edges(): List<Edge> {
            val q = LinkedList()
            val e = ArrayList()
            var head = 0
            var tail = 0
            q.add(this)
            tail++
            do {
                val n = q.remove()
                for (i in 0..3)
                    if (n.c[i] != null) {
                        q.add(n.c[i])
                        tail++
                        e.add(Edge(head + 1, tail, "ACGT".charAt(i)))
                    }
            } while (++head < tail)
            return e
        }
    }

    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val root = Node()
        while (stdin.hasNextLine())
            root.add(stdin.nextLine())
        for (e in root.edges())
            System.out.printf("%d %d %c\n", e.s, e.t, e.c)
    }
}
