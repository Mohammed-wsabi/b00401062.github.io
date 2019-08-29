import java.io.*
import java.util.*

object SORT {
    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val s = Arrays.stream(stdin.nextLine().split(" ")).map(???({ Integer() })).collect(Collectors.toList())
        val t = Arrays.stream(stdin.nextLine().split(" ")).map(???({ Integer() })).collect(Collectors.toList())
        val steps = ArrayList()
        for (i in 0..9) {
            if (s.get(i).equals(t.get(i))) continue
            val x = s.indexOf(t.get(i))
            Collections.reverse(s.subList(i, x + 1))
            steps.add(intArrayOf(i + 1, x + 1))
        }
        System.out.println(steps.size())
        for (step in steps)
            System.out.printf("%d %d\n", step[0], step[1])
    }
}
