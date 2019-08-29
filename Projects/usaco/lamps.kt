/*
ID: rombin82
LANG: JAVA
TASK: lamps
*/

import java.io.*
import java.util.*

object lamps {
    private fun move1(stdin: BooleanArray): BooleanArray {
        val out = stdin.clone()
        for (i in out.indices)
            out[i] = !out[i]
        return out
    }

    private fun move2(stdin: BooleanArray): BooleanArray {
        val out = stdin.clone()
        for (i in out.indices)
            if (i % 2 == 0)
                out[i] = !out[i]
        return out
    }

    private fun move3(stdin: BooleanArray): BooleanArray {
        val out = stdin.clone()
        for (i in out.indices)
            if (i % 2 == 1)
                out[i] = !out[i]
        return out
    }

    private fun move4(stdin: BooleanArray): BooleanArray {
        val out = stdin.clone()
        for (i in out.indices)
            if (i % 3 == 0)
                out[i] = !out[i]
        return out
    }

    private fun bool2str(stdin: BooleanArray): String {
        var out = ""
        for (i in stdin.indices)
            out += if (stdin[i]) '1' else '0'
        return out
    }

    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(File("lamps.in"))
        val stdout = PrintWriter(File("lamps.out"))
        Runtime.getRuntime().addShutdownHook(Thread { stdout.flush() })
        val N = stdin.nextInt()
        val C = stdin.nextInt()
        val on_set = ArrayList()
        val off_set = ArrayList()
        run {
            var num = stdin.nextInt()
            while (num != -1) {
                on_set.add(num - 1)
                num = stdin.nextInt()
            }
        }
        var num = stdin.nextInt()
        while (num != -1) {
            off_set.add(num - 1)
            num = stdin.nextInt()
        }
        val lights = BooleanArray(N)
        Arrays.fill(lights, true)
        val dict = intArrayOf(0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4)
        val moves = arrayOf(lights, move1(lights), move2(lights), move1(move2(lights)), move3(lights), move1(move3(lights)), move2(move3(lights)), move1(move2(move3(lights))), move4(lights), move1(move4(lights)), move2(move4(lights)), move1(move2(move4(lights))), move3(move4(lights)), move1(move3(move4(lights))), move2(move3(move4(lights))), move1(move2(move3(move4(lights)))))
        val list = ArrayList()
        for (i in 0..15)
            if (C - dict[i] >= 0 && (C - dict[i]) % 2 == 0) {
                var legal = true
                for (on in on_set)
                    if (!moves[i][on])
                        legal = false
                for (off in off_set)
                    if (moves[i][off])
                        legal = false
                if (legal)
                    list.add(bool2str(moves[i]))
            }
        Collections.sort(list)
        if (list.size() === 0)
            stdout.println("IMPOSSIBLE")
        else
            for (str in list)
                stdout.println(str)
    }
}
