/*
ID: rombin82
LANG: JAVA
TASK: milk3
*/

import java.io.*
import java.util.*

object milk3 {
    private var visited: Array<Array<BooleanArray>>? = null
    private var result: BooleanArray? = null
    private var capacities: IntArray? = null
    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(File("milk3.in"))
        val stdout = PrintWriter(File("milk3.out"))
        Runtime.getRuntime().addShutdownHook(Thread { stdout.flush() })
        capacities = intArrayOf(stdin.nextInt(), stdin.nextInt(), stdin.nextInt())
        val values = intArrayOf(0, 0, capacities!![2])
        visited = Array(capacities!![0] + 1) { Array(capacities!![1] + 1) { BooleanArray(capacities!![2] + 1) } }
        result = BooleanArray(capacities!![2] + 1)
        solve(values)
        val indices = ArrayList()
        for (i in result!!.indices)
            if (result!![i])
                indices.add(i)
        for (i in 0 until indices.size()) {
            stdout.print(indices.get(i))
            stdout.print(if (i == indices.size() - 1) '\n' else ' ')
        }
    }

    private fun solve(values: IntArray) {
        if (visited!![values[0]][values[1]][values[2]])
            return
        visited!![values[0]][values[1]][values[2]] = true
        if (values[0] == 0)
            result[values[2]] = true
        for (i in 0..2)
            for (j in 0..2)
                if (i != j) {
                    val values_new = pour(values, i, j)
                    solve(values_new)
                }
    }

    private fun pour(values: IntArray, i: Int, j: Int): IntArray {
        val result = values.clone()
        if (result[j] + result[i] <= capacities!![j]) {
            result[j] += result[i]
            result[i] = 0
            return result
        } else {
            result[i] -= capacities!![j] - result[j]
            result[j] = capacities!![j]
            return result
        }
    }
}
