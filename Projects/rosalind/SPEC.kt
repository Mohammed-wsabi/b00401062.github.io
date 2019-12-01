package rosalind

import java.io.IOException
import java.util.*
import kotlin.math.abs

object SPEC {
    private val MASS: Map<Char, Double> = mapOf(
        'A' to 71.03711, 'C' to 103.00919,
        'D' to 115.02694, 'E' to 129.04259,
        'F' to 147.06841, 'G' to 57.02146,
        'H' to 137.05891, 'I' to 113.08406,
        'K' to 128.09496, 'L' to 113.08406,
        'M' to 131.04049, 'N' to 114.04293,
        'P' to 97.05276, 'Q' to 128.05858,
        'R' to 156.10111, 'S' to 87.03203,
        'T' to 101.04768, 'V' to 99.06841,
        'W' to 186.07931, 'Y' to 163.06333
    )

    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        var mass = stdin.nextDouble()
        while (stdin.hasNext()) {
            val diff = stdin.nextDouble() - mass
            mass += diff
            print(
                MASS.entries.stream().
                    filter { abs(it.value - diff) < 1e-4 }.
                    map<Char> { it.key }.
                    toArray()
            )
        }
        println()
        stdin.close()
    }
}