import java.io.*
import java.util.*

internal object BINIM {
    private val ZERO = Integer('0'.toInt())
    private val ONE = Integer('1'.toInt())
    private val PLAYERS = arrayOf("Dee", "Dum")
    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        var T = stdin.nextInt()
        while (T-- > 0) {
            var N = stdin.nextInt()
            val turn = if (stdin.next().equals("Dum")) 1 else 0
            val counts = IntArray(2)
            while (N-- > 0) {
                val stack = stdin.next()
                val who = if (stack.charAt(0) === '1') ONE else ZERO
                counts[stack.charAt(0) - '0'] += stack.chars().filter(???({ who.equals() })).count()
            }
            System.out.println(if (counts[turn] > counts[1 - turn]) PLAYERS[turn] else PLAYERS[1 - turn])
        }
    }
}
