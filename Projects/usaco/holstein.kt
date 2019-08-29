/*
ID: rombin82
LANG: JAVA
TASK: holstein
*/

import java.io.*
import java.util.*

object holstein {
    private var V: Int = 0
    private var G: Int = 0
    private var brands: Array<IntArray>? = null
    private fun minScoop(needs: IntArray, start: Int): IntArray? {
        if (Arrays.stream(needs).allMatch({ x -> x <= 0 }))
            return IntArray(G)
        else if (start >= G)
            return null
        val scoop0 = minScoop(needs, start + 1)
        for (j in 0 until V)
            needs[j] -= brands!![start][j]
        val scoop1 = minScoop(needs, start + 1)
        for (j in 0 until V)
            needs[j] += brands!![start][j]
        if (scoop1 != null)
            scoop1[start] = 1
        if (scoop0 == null && scoop1 == null)
            return null
        else if (scoop0 == null)
            return scoop1
        else if (scoop1 == null)
            return scoop0
        else if (Arrays.stream(scoop1).sum() <= Arrays.stream(scoop0).sum())
            return scoop1
        return scoop0
    }

    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(File("holstein.in"))
        val stdout = PrintWriter(File("holstein.out"))
        Runtime.getRuntime().addShutdownHook(Thread { stdout.flush() })
        V = stdin.nextInt()
        val needs = IntArray(V)
        for (i in 0 until V)
            needs[i] = stdin.nextInt()
        G = stdin.nextInt()
        brands = Array(G) { IntArray(V) }
        for (i in 0 until G)
            for (j in 0 until V)
                brands!![i][j] = stdin.nextInt()
        val scoop_min = minScoop(needs, 0)
        stdout.print(Arrays.stream(scoop_min).sum())
        for (i in scoop_min!!.indices)
            if (scoop_min[i] == 1)
                stdout.print(" " + (i + 1))
        stdout.println()
    }
}
