import java.io.*
import java.util.*

internal object RUNDIR {
    private enum class Dir private constructor(private val dir: Int) {
        LT(-1), RT(1);

        fun toInt(): Int {
            return this.dir
        }
    }

    private class Child internal constructor(internal var x: Int, internal var v: Int) {
        internal var dir: Dir? = null
        internal fun setDir(dir: Dir): Child {
            this.dir = dir
            return this
        }

        fun time(that: Child): Double {
            val x = that.x - this.x
            val v = that.v * that.dir!!.toInt() - this.v * this.dir!!.toInt()
            if (v == 0) return Double.POSITIVE_INFINITY
            val time = x.toDouble() / v
            return if (time > 0) Double.POSITIVE_INFINITY else -time
        }
    }

    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        var t = stdin.nextInt()
        while (t-- > 0) {
            val n = stdin.nextInt()
            val children = arrayOfNulls<Child>(n)
            var LT = Double.POSITIVE_INFINITY
            var RT = Double.POSITIVE_INFINITY
            for (i in 0 until n)
                children[i] = Child(stdin.nextInt(), stdin.nextInt())
            Arrays.sort(children, Comparator.comparingInt({ child -> child.x }))
            for (i in 1 until n) {
                val lt = Math.max(
                        Math.min(LT, children[i].setDir(Dir.LT).time(children[i - 1].setDir(Dir.LT))),
                        Math.min(RT, children[i].setDir(Dir.LT).time(children[i - 1].setDir(Dir.RT))))
                val rt = Math.max(
                        Math.min(LT, children[i].setDir(Dir.RT).time(children[i - 1].setDir(Dir.LT))),
                        Math.min(RT, children[i].setDir(Dir.RT).time(children[i - 1].setDir(Dir.RT))))
                LT = lt
                RT = rt
            }
            val optimal = Math.max(LT, RT)
            System.out.println(if (optimal == Double.POSITIVE_INFINITY) -1 else optimal)
        }
    }
}
