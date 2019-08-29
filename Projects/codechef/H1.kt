import java.io.*
import java.util.*

internal object H1 {
    private val primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17)
    private val pairs = arrayOf(intArrayOf(0, 1), intArrayOf(1, 2), intArrayOf(3, 4), intArrayOf(4, 5), intArrayOf(6, 7), intArrayOf(7, 8), intArrayOf(0, 3), intArrayOf(1, 4), intArrayOf(2, 5), intArrayOf(3, 6), intArrayOf(4, 7), intArrayOf(5, 8))
    private val steps = HashMap()
    private fun bfs(s: String) {
        steps.put(s, 0)
        val queue = object : LinkedList<String>() {
            init {
                add(s)
            }
        }
        while (queue.size() > 0) {
            val u = queue.remove()
            val d = steps.get(u)
            for (pair in pairs) {
                val a = u.charAt(pair[0])
                val b = u.charAt(pair[1])
                if (!primes.contains(a - '0' + b.toInt() - '0'.toInt())) continue
                val v = u.replace(a, '@').replace(b, a).replace('@', b)
                if (steps.containsKey(v)) continue
                steps.put(v, d + 1)
                queue.add(v)
            }
        }
    }

    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        bfs("123456789")
        var t = Integer.parseInt(stdin.nextLine())
        while (t-- > 0) {
            stdin.nextLine()
            var puzzle = ""
            for (i in 0..2)
                puzzle += stdin.nextLine().replaceAll(" ", "")
            System.out.println(if (steps.containsKey(puzzle)) steps.get(puzzle) else Integer(-1))
        }
    }
}
