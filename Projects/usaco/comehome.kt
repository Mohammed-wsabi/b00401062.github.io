/*
ID: rombin82
LANG: JAVA
TASK: comehome
*/

import java.io.*
import java.util.*
import java.util.stream.IntStream

object comehome {
    private enum class Color {
        WHITE, GRAY, BLACK
    }

    private class Edge internal constructor(internal var node: Int, internal var weight: Int)
    private class Node {
        internal var dist = Integer.MAX_VALUE
        internal var edges: List<Edge> = ArrayList<Edge>()
        internal var color = Color.WHITE
    }

    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(File("comehome.in"))
        val stdout = PrintWriter(File("comehome.out"))
        Runtime.getRuntime().addShutdownHook(Thread { stdout.flush() })
        val graph = arrayOfNulls<Node>('z' - 'A' + 1)
        for (i in graph.indices)
            graph[i] = Node()
        val P = stdin.nextInt()
        for (i in 0 until P) {
            val u = stdin.next().charAt(0) - 'A'
            val v = stdin.next().charAt(0) - 'A'
            val w = stdin.nextInt()
            graph[u].edges.add(Edge(v, w))
            graph[v].edges.add(Edge(u, w))
        }
        val list = ArrayList<Node>()
        graph['Z' - 'A'].dist = 0
        graph['Z' - 'A'].color = Color.GRAY
        list.add(graph['Z' - 'A'])
        while (!list.isEmpty()) {
            val node = list.stream().min(Comparator.comparing(???({ it.getDist() }))).get()
            list.remove(node)
            node.color = Color.BLACK
            node.edges.forEach({ edge ->
                if (graph[edge.node].color != Color.BLACK)
                    graph[edge.node].dist = Math.min(graph[edge.node].dist, node.dist + edge.weight)
                if (graph[edge.node].color == Color.WHITE) {
                    graph[edge.node].color = Color.GRAY
                    list.add(graph[edge.node])
                }
            })
        }
        val min = Arrays.stream(graph).limit('Z' - 'A').min(Comparator.comparing(???({ it.getDist() }))).get().dist
        val id = 'A' + IntStream.range(0, 'Z' - 'A').filter({ x -> graph[x].dist == min }).findFirst().getAsInt()
        stdout.println(String.format("%c %d", id, min))
    }
}
