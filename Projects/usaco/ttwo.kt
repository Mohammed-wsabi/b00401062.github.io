/*
ID: rombin82
LANG: JAVA
TASK: ttwo
*/

import java.io.File
import java.io.IOException
import java.io.PrintWriter
import java.util.Arrays
import java.util.Scanner

object ttwo {
    private enum class Direction private constructor(val value: Int) {
        NORTH(0), EAST(1), SOUTH(2), WEST(3)
    }

    private class Player internal constructor(internal var coordinate: IntArray?, internal var direction: Direction?) {
        private val DELTA = arrayOf(intArrayOf(-1, 0), intArrayOf(0, 1), intArrayOf(1, 0), intArrayOf(0, -1))
        private val TURN = arrayOf(Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.NORTH)
        internal fun move(obstacles: Array<BooleanArray>) {
            val i = direction!!.value
            val next = intArrayOf(coordinate!![0] + DELTA[i][0], coordinate!![1] + DELTA[i][1])
            if (obstacles[next[0]][next[1]]) {
                direction = TURN[i]
            } else {
                coordinate[0] = next[0]
                coordinate[1] = next[1]
            }
        }
    }

    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(File("ttwo.in"))
        val stdout = PrintWriter(File("ttwo.out"))
        Runtime.getRuntime().addShutdownHook(Thread { stdout.flush() })
        val obstacles = Array(12) { BooleanArray(12) }
        for (i in 0..11) {
            Arrays.fill(obstacles[i], true)
        }
        var farmer: Player? = null
        var cow: Player? = null
        for (i in 1..10) {
            val line = stdin.next()
            for (j in 1..10) {
                when (line.charAt(j - 1)) {
                    '.' -> obstacles[i][j] = false
                    'F' -> {
                        farmer = Player(intArrayOf(i, j), Direction.NORTH)
                        obstacles[i][j] = false
                    }
                    'C' -> {
                        cow = Player(intArrayOf(i, j), Direction.NORTH)
                        obstacles[i][j] = false
                    }
                    else -> {
                    }
                }
            }
        }
        for (i in 1..160000) {
            farmer!!.move(obstacles)
            cow!!.move(obstacles)
            if (Arrays.equals(farmer.coordinate, cow.coordinate)) {
                stdout.println(i)
                return
            }
        }
        stdout.println(0)
    }
}
