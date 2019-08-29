/*
ID: rombin82
LANG: JAVA
TASK: maze1
*/

import java.io.*
import java.util.*

object maze1 {
    private fun entrances(maze: Array<CharArray>): List<IntArray> {
        val entrances = ArrayList()
        var i = 1
        while (i < maze.size) {
            if (maze[i][0] == ' ') {
                entrances.add(intArrayOf(i, 1))
                maze[i][0] = '|'
            }
            if (maze[i][maze[0].size - 1] == ' ') {
                entrances.add(intArrayOf(i, maze[0].size - 2))
                maze[i][maze[0].size - 1] = '|'
            }
            i += 2
        }
        var j = 1
        while (j < maze[0].size) {
            if (maze[0][j] == ' ') {
                entrances.add(intArrayOf(1, j))
                maze[0][j] = '-'
            }
            if (maze[maze.size - 1][j] == ' ') {
                entrances.add(intArrayOf(maze.size - 2, j))
                maze[maze.size - 1][j] = '-'
            }
            j += 2
        }
        return entrances
    }

    private fun neighbors(maze: Array<CharArray>, vertex: IntArray): List<IntArray> {
        val neighbors = ArrayList()
        val DELTA = arrayOf(intArrayOf(-1, 0), intArrayOf(0, 1), intArrayOf(1, 0), intArrayOf(0, -1))
        for (delta in DELTA)
            if (maze[vertex[0] + delta[0]][vertex[1] + delta[1]] == ' ')
                neighbors.add(intArrayOf(vertex[0] + delta[0] * 2, vertex[1] + delta[1] * 2))
        return neighbors
    }

    private fun search(maze: Array<CharArray>, source: IntArray): Array<IntArray> {
        val w = maze[0].size
        val h = maze.size
        val visited = Array(h) { IntArray(w) }
        visited[source[0]][source[1]] = 1
        val queue = LinkedList()
        queue.add(source)
        while (!queue.isEmpty()) {
            val vertex = queue.remove()
            for (neighbor in neighbors(maze, vertex))
                if (visited[neighbor[0]][neighbor[1]] == 0) {
                    visited[neighbor[0]][neighbor[1]] = visited[vertex[0]][vertex[1]] + 1
                    queue.add(neighbor)
                }
        }
        return visited
    }

    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(File("maze1.in"))
        val stdout = PrintWriter(File("maze1.out"))
        Runtime.getRuntime().addShutdownHook(Thread { stdout.flush() })
        val w = 2 * stdin.nextInt() + 1
        val h = 2 * stdin.nextInt() + 1
        stdin.nextLine()
        val maze = arrayOfNulls<CharArray>(h)
        for (i in 0 until h)
            maze[i] = stdin.nextLine().toCharArray()
        val entrances = entrances(maze)
        val visited0 = search(maze, entrances[0])
        val visited1 = search(maze, entrances[1])
        val visited = Array(h) { IntArray(w) }
        var i = 1
        while (i < h) {
            {
                var j = 1
                while (j < w) {
                    visited[i][j] = Math.min(visited0[i][j], visited1[i][j])
                    j += 2
                }
            }
            i += 2
        }
        stdout.println(Arrays.stream(visited).flatMapToInt(???({ Arrays.stream() })).max().getAsInt())
    }
}
