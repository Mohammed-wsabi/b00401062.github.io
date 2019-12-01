package rosalind

import java.io.IOException
import java.util.*
import java.util.stream.Collectors

object PROT {
    private val CODON: Map<String, Char?> = mapOf(
        "TTT" to 'F', "CTT" to 'L', "ATT" to 'I', "GTT" to 'V',
        "TTC" to 'F', "CTC" to 'L', "ATC" to 'I', "GTC" to 'V',
        "TTA" to 'L', "CTA" to 'L', "ATA" to 'I', "GTA" to 'V',
        "TTG" to 'L', "CTG" to 'L', "ATG" to 'M', "GTG" to 'V',
        "TCT" to 'S', "CCT" to 'P', "ACT" to 'T', "GCT" to 'A',
        "TCC" to 'S', "CCC" to 'P', "ACC" to 'T', "GCC" to 'A',
        "TCA" to 'S', "CCA" to 'P', "ACA" to 'T', "GCA" to 'A',
        "TCG" to 'S', "CCG" to 'P', "ACG" to 'T', "GCG" to 'A',
        "TAT" to 'Y', "CAT" to 'H', "AAT" to 'N', "GAT" to 'D',
        "TAC" to 'Y', "CAC" to 'H', "AAC" to 'N', "GAC" to 'D',
        "TAA" to null, "CAA" to 'Q', "AAA" to 'K', "GAA" to 'E',
        "TAG" to null, "CAG" to 'Q', "AAG" to 'K', "GAG" to 'E',
        "TGT" to 'C', "CGT" to 'R', "AGT" to 'S', "GGT" to 'G',
        "TGC" to 'C', "CGC" to 'R', "AGC" to 'S', "GGC" to 'G',
        "TGA" to null, "CGA" to 'R', "AGA" to 'R', "GGA" to 'G',
        "TGG" to 'W', "CGG" to 'R', "AGG" to 'R', "GGG" to 'G'
    )

    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val seq = stdin.next()
        println(
            Arrays.stream(seq.substring(0, seq.length - 3).
                split("(?<=\\G...)".toRegex()).
                toTypedArray()).
                map { o: String? -> CODON[o] }.
                map { obj: Char? -> obj.toString() }.
                collect(Collectors.joining())
        )
        stdin.close()
    }
}