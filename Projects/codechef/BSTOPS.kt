import java.io.*
import java.util.*

internal object BSTOPS {
    private class Node internal constructor(internal var x: Int, internal var p: Long) {
        internal var lt: Node? = null
        internal var rt: Node? = null
    }

    private fun insert(root: Node?, x: Int, p: Long): Node {
        if (root == null) {
            System.out.println(p)
            return Node(x, p)
        } else if (x < root.x)
            root.lt = insert(root.lt, x, p * 2)
        else if (x > root.x)
            root.rt = insert(root.rt, x, p * 2 + 1)
        return root
    }

    private fun delete(root: Node, x: Int): Node? {
        if (x < root.x)
            root.lt = delete(root.lt!!, x)
        else if (x > root.x)
            root.rt = delete(root.rt!!, x)
        else {
            System.out.println(root.p)
            if (root.lt == null)
                return root.rt
            else if (root.rt == null)
                return root.lt
            else {
                var successor = root.rt
                while (successor!!.lt != null)
                    successor = successor.lt
                root.x = successor.x
                root.rt = delete(root.rt!!, successor.x)
            }
        }
        return root
    }

    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        var n = stdin.nextInt()
        var root: Node? = null
        while (n-- > 0) {
            val c = stdin.next().charAt(0)
            val x = stdin.nextInt()
            when (c) {
                'i' -> root = insert(root, x, 1)
                'd' -> root = delete(root!!, x)
            }
        }
    }
}
