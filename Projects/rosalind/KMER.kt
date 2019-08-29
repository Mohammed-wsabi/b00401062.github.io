import java.io.*
import java.util.*
import java.util.stream.*

object KMER {
    private fun str2int(str: String): Int {
        return IntStream.range(0, 4).map({ x -> Math.pow(4, 3 - x) as Int * "ACGT".indexOf(str.charAt(x)) }).sum()
    }

    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        var read = ""
        val counts = IntArray(256)
        stdin.next()
        while (stdin.hasNext()) {
            read += stdin.next()
            for (i in 0 until read.length() - 3)
                counts[str2int(read.substring(i, i + 4))]++
            read = read.substring(read.length() - 3)
        }
        System.out.println(Arrays.stream(counts).mapToObj(???({ String.valueOf() })).collect(Collectors.joining(" ")))
    }
}
