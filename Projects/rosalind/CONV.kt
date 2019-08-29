import java.io.*
import java.util.*

object CONV {
    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val S1 = Arrays.stream(stdin.nextLine().split(" ")).mapToDouble(???({ Double.parseDouble() })).toArray()
        val S2 = Arrays.stream(stdin.nextLine().split(" ")).mapToDouble(???({ Double.parseDouble() })).toArray()
        val counter = HashMap()
        for (s1 in S1)
            for (s2 in S2) {
                val key = String.format("%.5f", s1 - s2)
                counter.put(key, counter.getOrDefault(key, 0) + 1)
            }
        counter.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(1).forEach({ e ->
            System.out.println(e.getValue())
            System.out.println(e.getKey())
        })
    }
}
