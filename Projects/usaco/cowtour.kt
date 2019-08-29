/*
ID: rombin82
LANG: JAVA
TASK: cowtour
*/

import java.io.*
import java.util.*
import java.util.stream.IntStream

object cowtour {
    private enum class Color {
        WHITE, GRAY, BLACK
    }

    private class Node internal constructor(internal var x: Int, internal var y: Int) {
        internal var dist = Double.POSITIVE_INFINITY
        internal var color = Color.WHITE
        internal var edges: List<Node> = ArrayList<Node>()
        internal fun getDist(that: Node): Double {
            return Math.sqrt(Math.pow(this.x - that.x, 2) + Math.pow(this.y - that.y, 2))
        }
    }

    private fun dijkstra(graph: Array<Node>, s: Int): DoubleArray {
        Arrays.stream(graph).forEach({ n ->
            n.dist = Double.POSITIVE_INFINITY
            n.color = Color.WHITE
        })
        val list = ArrayList<Node>()
        graph[s].dist = 0.0
        graph[s].color = Color.GRAY
        list.add(graph[s])
        while (!list.isEmpty()) {
            val node = list.stream().min(Comparator.comparing(???({ getDist() }))).get()
            list.remove(node)
            node.color = Color.BLACK
            node.edges.forEach({ neighbor ->
                if (neighbor.color !== Color.BLACK)
                    neighbor.dist = Math.min(neighbor.dist, node.dist + node.getDist(neighbor))
                if (neighbor.color === Color.WHITE) {
                    neighbor.color = Color.GRAY
                    list.add(neighbor)
                }
            })
        }
        return Arrays.stream(graph).mapToDouble(???({ getDist() })).toArray()
    }

    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(File("cowtour.in"))
        val stdout = PrintWriter(File("cowtour.out"))
        Runtime.getRuntime().addShutdownHook(Thread { stdout.flush() })
        val N = stdin.nextInt()
        val graph = arrayOfNulls<Node>(N)
        for (i in 0 until N)
            graph[i] = Node(stdin.nextInt(), stdin.nextInt())
        for (i in 0 until N) {
            val line = stdin.next()
            for (j in 0 until N)
                if (line.charAt(j) === '1')
                    graph[i].edges.add(graph[j])
        }
        val table = Array(N) { DoubleArray(N) }
        for (i in 0 until N)
            table[i] = dijkstra(graph, i)
        val maxDists = DoubleArray(N)
        for (i in 0 until N)
            maxDists[i] = Arrays.stream(table[i]).filter({ x -> x !== Double.POSITIVE_INFINITY }).max().getAsDouble()
        val diameters = DoubleArray(N)
        for (n in 0 until N) {
            val row = table[n]
            if (diameters[n] == 0.0) {
                val indices = IntStream.range(0, N).filter({ x -> row[x] != Double.POSITIVE_INFINITY }).toArray()
                var tmp = 0.0
                for (i in indices)
                    for (j in indices)
                        tmp = Math.max(tmp, table[i][j])
                val diameter = tmp
                Arrays.stream(indices).forEach({ x -> diameters[x] = diameter })
            }
        }
        var ans = Double.POSITIVE_INFINITY
        for (i in 0 until N) {
            for (j in i + 1 until N) {
                if (table[i][j] == Double.POSITIVE_INFINITY) {
                    val candidates = doubleArrayOf(diameters[i], diameters[j], maxDists[i] + maxDists[j] + graph[i].getDist(graph[j]))
                    ans = Math.min(ans, Arrays.stream(candidates).max().getAsDouble())
                }
            }
        }
        stdout.println(String.format("%.6f", ans))
    }
}
