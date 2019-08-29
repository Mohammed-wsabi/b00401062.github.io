import java.io.*
import java.util.*
import java.util.stream.*

object REAR {
    private fun reversal(s: List<Integer>, t: List<Integer>): Int {
        val n = s.size()
        val distance = object : HashMap<List<Integer>, Integer>() {
            init {
                put(s, 0)
            }
        }
        val queue = object : LinkedList<List<Integer>>() {
            init {
                add(s)
            }
        }
        while (queue.size() > 0 && !distance.containsKey(t)) {
            val u = ArrayList(queue.remove())
            val d = distance.get(u)
            for (i in 0 until n)
                for (j in i + 2..n) {
                    Collections.reverse(u.subList(i, j))
                    val v = ArrayList(u)
                    Collections.reverse(u.subList(i, j))
                    if (distance.containsKey(v)) continue
                    distance.put(v, d + 1)
                    queue.add(v)
                    if (v.equals(t)) return distance.get(t)
                }
        }
        return distance.get(t)
    }

    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        do {
            val s = Arrays.stream(stdin.nextLine().split(" ")).map(???({ Integer() })).collect(Collectors.toList())
            val t = Arrays.stream(stdin.nextLine().split(" ")).map(???({ Integer() })).collect(Collectors.toList())
            System.out.println(reversal(s, t))
        } while (stdin.hasNext() && stdin.nextLine().equals(""))
    }
}
