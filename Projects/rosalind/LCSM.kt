package rosalind

import java.io.IOException
import java.util.*

object LCSM {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val root = Node()
        var name: String? = null
        var seq: String? = null
        while (true) {
            if (!stdin.hasNext()) {
                for (i in 0 until seq!!.length) root.add(name, seq.substring(i))
                break
            }
            val line = stdin.next()
            if (line[0] == '>') {
                if (name != null) for (i in 0 until seq!!.length) root.add(name, seq.substring(i))
                name = line.substring(1)
                seq = ""
            } else seq += line
        }
        println(root.lcs())
        stdin.close()
    }

    private class Node {
        var edge = ""
        var names: MutableSet<String?> = HashSet()
        var children = arrayOfNulls<Node>(4)

        internal constructor() {}
        internal constructor(edge: String) {
            this.edge = edge
        }

        internal constructor(edge: String, names: MutableSet<String?>) {
            this.edge = edge
            this.names = names
        }

        fun add(name: String?, seq: String) {
            var pos: Int
            val min = Math.min(edge.length, seq.length)
            pos = 0
            while (pos < min) {
                if (edge[pos] != seq[pos]) break
                pos++
            }
            if (pos < edge.length) {
                val node = Node(edge.substring(pos), HashSet(names))
                for (i in 0..3) {
                    node.children[i] = children[i]
                    children[i] = null
                }
                children["ACGT".indexOf(edge[pos])] = node
                edge = edge.substring(0, pos)
            }
            if (pos < seq.length) {
                if (children["ACGT".indexOf(seq[pos])] == null) children["ACGT".indexOf(seq[pos])] = Node(seq.substring(pos))
                children["ACGT".indexOf(seq[pos])]!!.add(name, seq.substring(pos))
            }
            names.add(name)
        }

        fun lcs(): String {
            val lcs: MutableList<String> = ArrayList()
            lcs.add("")
            for (i in 0..3) if (children[i] != null && children[i]!!.names.size == names.size) lcs.add(children[i]!!.lcs())
            return edge + lcs.stream().max(Comparator.comparingInt { obj: String -> obj.length }).get()
        }
    }
}