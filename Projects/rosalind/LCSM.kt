import java.io.*
import java.util.*

object LCSM {
    private class Node {
        internal var edge = ""
        internal var names: Set<String> = HashSet<String>()
        internal var children = arrayOfNulls<Node>(4)

        internal constructor() {}
        internal constructor(edge: String) {
            this.edge = edge
        }

        internal constructor(edge: String, names: Set<String>) {
            this.edge = edge
            this.names = names
        }

        fun add(name: String?, seq: String) {
            var pos: Int
            val min = Math.min(edge.length(), seq.length())
            pos = 0
            while (pos < min) {
                if (edge.charAt(pos) !== seq.charAt(pos))
                    break
                pos++
            }
            if (pos < edge.length()) {
                val node = Node(edge.substring(pos), HashSet<String>(names))
                for (i in 0..3) {
                    node.children[i] = children[i]
                    children[i] = null
                }
                children["ACGT".indexOf(edge.charAt(pos))] = node
                edge = edge.substring(0, pos)
            }
            if (pos < seq.length()) {
                if (children["ACGT".indexOf(seq.charAt(pos))] == null)
                    children["ACGT".indexOf(seq.charAt(pos))] = Node(seq.substring(pos))
                children["ACGT".indexOf(seq.charAt(pos))].add(name, seq.substring(pos))
            }
            names.add(name)
        }

        fun lcs(): String {
            val lcs = ArrayList()
            lcs.add("")
            for (i in 0..3)
                if (children[i] != null && children[i].names.size() === names.size())
                    lcs.add(children[i].lcs())
            return edge + lcs.stream().max(Comparator.comparingInt(???({ String.length() }))).get()
        }
    }

    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val root = Node()
        var name: String? = null
        var seq: String? = null
        while (true) {
            if (!stdin.hasNext()) {
                for (i in 0 until seq!!.length())
                    root.add(name, seq!!.substring(i))
                break
            }
            val line = stdin.next()
            if (line.charAt(0) === '>') {
                if (name != null)
                    for (i in 0 until seq!!.length())
                        root.add(name, seq!!.substring(i))
                name = line.substring(1)
                seq = ""
            } else
                seq += line
        }
        System.out.println(root.lcs())
        stdin.close()
    }
}
