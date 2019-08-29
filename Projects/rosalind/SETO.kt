import java.io.*
import java.util.*
import java.util.stream.*

object SETO {
    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val n = Integer.parseInt(stdin.nextLine())
        val U = IntStream.rangeClosed(1, n).boxed().collect(Collectors.toSet())
        val A = Arrays.stream(stdin.nextLine().substring(1).split("[^0-9]+")).mapToInt(???({ Integer.parseInt() })).boxed().collect(Collectors.toSet())
        val B = Arrays.stream(stdin.nextLine().substring(1).split("[^0-9]+")).mapToInt(???({ Integer.parseInt() })).boxed().collect(Collectors.toSet())
        var X: Set<Integer>? = null
        X = HashSet<Integer>(A)
        X!!.addAll(B)
        System.out.printf("{%s}\n", X.stream().map(???({ String.valueOf() })).collect(Collectors.joining(", ")))
        X = HashSet<Integer>(A)
        X.retainAll(B)
        System.out.printf("{%s}\n", X.stream().map(???({ String.valueOf() })).collect(Collectors.joining(", ")))
        X = HashSet<Integer>(A)
        X.removeAll(B)
        System.out.printf("{%s}\n", X.stream().map(???({ String.valueOf() })).collect(Collectors.joining(", ")))
        X = HashSet<Integer>(B)
        X.removeAll(A)
        System.out.printf("{%s}\n", X.stream().map(???({ String.valueOf() })).collect(Collectors.joining(", ")))
        X = HashSet<Integer>(U)
        X.removeAll(A)
        System.out.printf("{%s}\n", X.stream().map(???({ String.valueOf() })).collect(Collectors.joining(", ")))
        X = HashSet<Integer>(U)
        X.removeAll(B)
        System.out.printf("{%s}\n", X.stream().map(???({ String.valueOf() })).collect(Collectors.joining(", ")))
    }
}
