import java.io.*
import java.util.*

object SPEC {
    private val MASS = object : HashMap() {
        init {
            put('A', 71.03711)
            put('C', 103.00919)
            put('D', 115.02694)
            put('E', 129.04259)
            put('F', 147.06841)
            put('G', 57.02146)
            put('H', 137.05891)
            put('I', 113.08406)
            put('K', 128.09496)
            put('L', 113.08406)
            put('M', 131.04049)
            put('N', 114.04293)
            put('P', 97.05276)
            put('Q', 128.05858)
            put('R', 156.10111)
            put('S', 87.03203)
            put('T', 101.04768)
            put('V', 99.06841)
            put('W', 186.07931)
            put('Y', 163.06333)
        }
    }

    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        var mass = stdin.nextDouble()
        while (stdin.hasNext()) {
            val diff = stdin.nextDouble() - mass
            mass += diff
            System.out.print(MASS.entrySet().stream().filter({ e -> Math.abs(e.getValue() - diff) < 1e-4 }).map(???({ Map.Entry.getKey() })).toArray(Character[]::new  /* Currently unsupported in Kotlin */)[0])
        }
        System.out.println()
    }
}
