import java.io.*
import java.util.*
import java.util.stream.*

object LEXV {
    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val symbols = Arrays.asList(stdin.nextLine().split(" "))
        val n = stdin.nextInt()
        val N = symbols.size()
        val stack = Stack()
        while (true) {
            if (stack.size() < n) {
                stack.push(0)
            } else {
                try {
                    var last: Int
                    while ((last = stack.pop()) == N - 1);
                    stack.push(last + 1)
                } catch (e: EmptyStackException) {
                    return
                }

            }
            System.out.println(stack.stream().map(???({ symbols.get() })).collect(Collectors.joining()))
        }
    }
}
