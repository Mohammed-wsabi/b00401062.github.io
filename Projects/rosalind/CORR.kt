import java.io.*
import java.util.*
import java.util.stream.*

object CORR {
    private val COMPLEMENT = object : HashMap<Character, Character>() {
        init {
            put('A', 'T')
            put('C', 'G')
            put('G', 'C')
            put('T', 'A')
        }
    }

    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val reads = HashSet()
        val table = HashMap()
        while (stdin.hasNext()) {
            stdin.next()
            val read = stdin.next()
            val revc = StringBuilder(read).reverse().chars().mapToObj({ x -> x }).map(???({ COMPLEMENT.get() })).map(???({ String.valueOf() })).collect(Collectors.joining())
            reads.add(read)
            table.put(read, if (table.containsKey(read)) table.get(read) + 1 else 1)
            table.put(revc, if (table.containsKey(revc)) table.get(revc) + 1 else 1)
        }
        val n = reads.stream().toArray(String[]::new  /* Currently unsupported in Kotlin */)[0].length()
        for (pat in reads)
            if (table.get(pat) === 1)
                for (seq in table.keySet())
                    if (table.get(seq) > 1 && IntStream.range(0, n).filter({ i -> seq.charAt(i) !== pat.charAt(i) }).count() === 1) {
                        System.out.println(pat + "->" + seq)
                        break
                    }
        stdin.close()
    }
}
