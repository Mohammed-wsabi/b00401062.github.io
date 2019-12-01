package rosalind

import java.io.IOException
import java.util.*
import java.util.function.Consumer
import java.util.stream.Collectors

object ORF {
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

    private val COMPLEMENT: Map<Char, Char> = mapOf(
        'A' to 'T', 'C' to 'G', 'G' to 'C', 'T' to 'A'
    )

    private fun translate(dna: String): Set<String> {
        val orfs: MutableSet<String> = HashSet()
        var orf: StringBuilder? = null
        var i = 0
        while (i < dna.length / 3) {
            if (dna.substring(i * 3, i * 3 + 3) == "ATG") {
                orf = StringBuilder()
                var aa: Char? = null
                while (i < dna.length / 3 && CODON[dna.substring(i * 3, i * 3 + 3)].also { aa = it } != null) {
                    orf.append(aa)
                    i++
                }
                if (aa == null) {
                    for (j in 0 until orf.length) {
                        if (orf[j] == 'M') {
                            orfs.add(orf.substring(j))
                        }
                    }
                }
            }
            i++
        }
        return orfs
    }

    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val stdout = System.out
        stdin.next()
        var seq = ""
        while (stdin.hasNext()) seq += stdin.next()
        val rev = seq.reversed().map { COMPLEMENT[it] }.joinToString("")
        val orfs: MutableSet<String> = HashSet()
        for (i in 0..2) {
            orfs.addAll(translate(seq.substring(i)))
            orfs.addAll(translate(rev.substring(i)))
        }
        orfs.forEach(Consumer { x: String? -> stdout.println(x) })
        stdin.close()
    }
}