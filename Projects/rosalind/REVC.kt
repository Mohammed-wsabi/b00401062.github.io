import java.io.*
import java.util.*

object REVC {
    private val COMPLEMENT = object : HashMap<Character, Character>() {
        init {
            put('A', 'T')
            put('C', 'G')
            put('G', 'C')
            put('T', 'A')
        }
    }

    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val seq = stdin.nextLine()
        System.out.println(StringBuilder(seq).reverse().chars().mapToObj({ x -> x }).map(???({ COMPLEMENT.get() })).map(???({ String.valueOf() })).collect(Collectors.joining()))
        stdin.close()
    }
}
