package rosalind

import java.util.*

object NWCK {
    private fun distance(tree: Array<String>, s: Int, t: Int): Int {
        var d = 0
        val stack: Stack<String> = object : Stack<String>() {
            init {
                push("$")
            }
        }
        for (i in s + 1 until t) {
            when (tree[i]) {
                "(" -> {
                    d += 1
                    stack.push("(")
                }
                ")" -> when (stack.peek()) {
                    "(" -> {
                        d -= 1
                        stack.pop()
                    }
                    "," -> {
                        d -= 1
                        stack.pop()
                        stack.push(")")
                    }
                    else -> {
                        d += 1
                        stack.push(")")
                    }
                }
                "," -> when (stack.peek()) {
                    "(", "," -> {
                    }
                    else -> {
                        d += 2
                        stack.push(",")
                    }
                }
                else -> {
                }
            }
        }
        return d
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        while (stdin.hasNext()) {
            val line = stdin.next()
            val tree = line.split("(?<=[(),;])|(?=[(),;])".toRegex()).toTypedArray()
            val s = Arrays.asList(*tree).indexOf(stdin.next())
            val t = Arrays.asList(*tree).indexOf(stdin.next())
            println(distance(tree, Math.min(s, t), Math.max(s, t)))
        }
        stdin.close()
    }
}