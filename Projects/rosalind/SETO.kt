package rosalind

import java.io.IOException
import java.util.*
import java.util.stream.Collectors
import java.util.stream.IntStream

object SETO {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val n = stdin.nextLine().toInt()
        val U = IntStream.rangeClosed(1, n).boxed().collect(Collectors.toSet())
        val A = Arrays.stream(stdin.nextLine().substring(1).split("[^0-9]+".toRegex()).toTypedArray()).mapToInt { s: String -> s.toInt() }.boxed().collect(Collectors.toSet())
        val B = Arrays.stream(stdin.nextLine().substring(1).split("[^0-9]+".toRegex()).toTypedArray()).mapToInt { s: String -> s.toInt() }.boxed().collect(Collectors.toSet())
        var X: MutableSet<Int>? = null
        X = HashSet(A)
        X.addAll(B)
        System.out.printf("{%s}\n", X.stream().map { obj: Int -> obj.toString() }.collect(Collectors.joining(", ")))
        X = HashSet(A)
        X.retainAll(B)
        System.out.printf("{%s}\n", X.stream().map { obj: Int -> obj.toString() }.collect(Collectors.joining(", ")))
        X = HashSet(A)
        X.removeAll(B)
        System.out.printf("{%s}\n", X.stream().map { obj: Int -> obj.toString() }.collect(Collectors.joining(", ")))
        X = HashSet(B)
        X.removeAll(A)
        System.out.printf("{%s}\n", X.stream().map { obj: Int -> obj.toString() }.collect(Collectors.joining(", ")))
        X = HashSet(U)
        X.removeAll(A)
        System.out.printf("{%s}\n", X.stream().map { obj: Int -> obj.toString() }.collect(Collectors.joining(", ")))
        X = HashSet(U)
        X.removeAll(B)
        System.out.printf("{%s}\n", X.stream().map { obj: Int -> obj.toString() }.collect(Collectors.joining(", ")))
        stdin.close()
    }
}