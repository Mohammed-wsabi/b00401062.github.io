import java.io.*
import java.util.*
import java.util.stream.*

object DBRU {
    private val COMPLEMENT = object : HashMap() {
        init {
            put('A', 'T')
            put('C', 'G')
            put('G', 'C')
            put('T', 'A')
        }
    }

    private fun rc(seq: String): String {
        return StringBuilder(seq)
                .reverse()
                .chars()
                .mapToObj({ x -> x })
                .map(???({ COMPLEMENT.get() }))
        .map(???({ String.valueOf() }))
        .collect(Collectors.joining())
    }

    private fun add(map: Map<String, Set<String>>, seq: String) {
        val k = seq.substring(0, seq.length() - 1)
        val v = seq.substring(1)
        if (map.containsKey(k))
            map[k].add(v)
        else
            map.put(k, object : HashSet<String>() {
                init {
                    add(v)
                }
            })
    }

    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val map = HashMap()
        while (stdin.hasNextLine()) {
            val seq = stdin.nextLine()
            add(map, seq)
            add(map, rc(seq))
        }
        for (entry in map.entrySet()) {
            val k = entry.getKey()
            for (v in entry.getValue())
                System.out.printf("(%s, %s)\n", k, v)
        }
    }
}
