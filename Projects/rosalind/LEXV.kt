package rosalind

import java.io.IOException
import java.util.*
import java.util.stream.Collectors

object LEXV {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val symbols = Arrays.asList(*stdin.nextLine().split(" ".toRegex()).toTypedArray())
        val n = stdin.nextInt()
        val N = symbols.size
        val stack = Stack<Int>()
        while (true) {
            if (stack.size < n) {
                stack.push(0)
            } else {
                try {
                    var last: Int
                    while (stack.pop().also { last = it } == N - 1);
                    stack.push(last + 1)
                } catch (e: EmptyStackException) {
                    break
                }
            }
            println(stack.stream().map { i: Int? -> symbols[i!!] }.collect(Collectors.joining()))
        }
        stdin.close()
    }
}