import java.io.*
import java.util.*

object REVP {
    private val COMPLEMENT = object : HashMap<Character, Character>() {
        init {
            put('A', 'T')
            put('C', 'G')
            put('G', 'C')
            put('T', 'A')
        }
    }

    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        var seq = ""
        stdin.next()
        while (stdin.hasNext())
            seq += stdin.next()
        for (s in 0 until seq.length() - 1)
            if (COMPLEMENT.get(seq.charAt(s)) === seq.charAt(s + 1)) {
                var i = 1
                while (s >= i && s + i + 1 < seq.length() && COMPLEMENT.get(seq.charAt(s - i)) === seq.charAt(s + i + 1)) {
                    if (4 <= 2 * i + 2 && 2 * i + 2 <= 12)
                        System.out.println(String.format("%d %d", s - i + 1, 2 * i + 2))
                    i++
                }
            }
        stdin.close()
    }
}
