private class Graph(p: String) {

    private data class Edge(val s: Int, val t: Int, val w: Char)

    private val edges: MutableList<MutableList<Edge>> = mutableListOf()

    private fun addNode(): Int {
        edges.add(mutableListOf())
        return edges.lastIndex
    }

    private fun addEdge(s: Int, t: Int, w: Char) {
        edges[s].add(Edge(s, t, w))
    }

    init {
        var i = 0
        while (i < p.length) {
            val t = addNode()
            val s = t - 1
            addEdge(s, t, p[i])
            i++
            if (i < p.length && p[i] == '*') {
                addEdge(t, s, ' ')
                i++
            }
        }
    }

    fun execute(s: String): Boolean {
        // TODO
        return false
    }
}

fun isMatch(s: String, p: String): Boolean {
    return Graph(p).execute(s)
}
