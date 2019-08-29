import java.io.*
import java.util.*

object NWCK {
    private fun distance(tree: Array<String>, s: Int, t: Int): Int {
        var d = 0
        val stack = object : Stack() {
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

    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        while (stdin.hasNext()) {
            val line = stdin.next()
            val tree = line.split("(?<=[(),;])|(?=[(),;])")
            val s = Arrays.asList(tree).indexOf(stdin.next())
            val t = Arrays.asList(tree).indexOf(stdin.next())
            System.out.println(distance(tree, Math.min(s, t), Math.max(s, t)))
        }
    }
}
