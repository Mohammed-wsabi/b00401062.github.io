/*
ID: rombin82
LANG: JAVA
TASK: castle
*/

import java.io.*
import java.util.*

object castle {
    private var width: Int = 0
    private var height: Int = 0
    private var walls: Array<IntArray>? = null
    private var rooms: Array<IntArray>? = null
    private var size: IntArray? = null
    private fun label(label: Int, i: Int, j: Int) {
        rooms!![i][j] = label
        if (rooms!![i][j - 1] == 0 && walls!![i][j] and 1 == 0 && walls!![i][j - 1] and 4 == 0)
            label(label, i, j - 1)
        if (rooms!![i - 1][j] == 0 && walls!![i][j] and 2 == 0 && walls!![i - 1][j] and 8 == 0)
            label(label, i - 1, j)
        if (rooms!![i][j + 1] == 0 && walls!![i][j] and 4 == 0 && walls!![i][j + 1] and 1 == 0)
            label(label, i, j + 1)
        if (rooms!![i + 1][j] == 0 && walls!![i][j] and 8 == 0 && walls!![i + 1][j] and 2 == 0)
            label(label, i + 1, j)
    }

    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(File("castle.in"))
        val stdout = PrintWriter(File("castle.out"))
        Runtime.getRuntime().addShutdownHook(Thread { stdout.flush() })
        width = stdin.nextInt()
        height = stdin.nextInt()
        walls = Array(height + 2) { IntArray(width + 2) }
        rooms = Array(height + 2) { IntArray(width + 2) }
        for (i in 1..height) {
            walls!![i][0] = 4
            walls!![i][width + 1] = 1
        }
        for (j in 1..width) {
            walls!![0][j] = 8
            walls!![height + 1][j] = 2
        }
        for (i in 1..height)
            for (j in 1..width)
                walls!![i][j] = stdin.nextInt()
        var room_cnt = 0
        for (i in 1..height)
            for (j in 1..width)
                if (rooms!![i][j] == 0)
                    label(++room_cnt, i, j)
        size = IntArray(room_cnt + 1)
        for (i in 1..height)
            for (j in 1..width)
                size!![rooms!![i][j]]++
        var room_max = IntArray(4)
        for (j in 1..width)
            for (i in height downTo 1) {
                if (rooms!![i][j] != rooms!![i - 1][j] && size!![rooms!![i][j]] + size!![rooms!![i - 1][j]] > room_max[0])
                    room_max = intArrayOf(size!![rooms!![i][j]] + size!![rooms!![i - 1][j]], i, j, 'N'.toInt())
                if (rooms!![i][j] != rooms!![i][j + 1] && size!![rooms!![i][j]] + size!![rooms!![i][j + 1]] > room_max[0])
                    room_max = intArrayOf(size!![rooms!![i][j]] + size!![rooms!![i][j + 1]], i, j, 'E'.toInt())
            }
        stdout.println(room_cnt)
        stdout.println(Arrays.stream(size).max().getAsInt())
        stdout.println(room_max[0])
        stdout.println(room_max[1].toString() + " " + room_max[2] + " " + room_max[3].toChar())
    }
}
