import java.io.*
import java.util.*
import java.util.stream.*

object ORF {
    private val CODON = object : HashMap<String, Character>() {
        init {
            put("TTT", 'F')
            put("CTT", 'L')
            put("ATT", 'I')
            put("GTT", 'V')
            put("TTC", 'F')
            put("CTC", 'L')
            put("ATC", 'I')
            put("GTC", 'V')
            put("TTA", 'L')
            put("CTA", 'L')
            put("ATA", 'I')
            put("GTA", 'V')
            put("TTG", 'L')
            put("CTG", 'L')
            put("ATG", 'M')
            put("GTG", 'V')
            put("TCT", 'S')
            put("CCT", 'P')
            put("ACT", 'T')
            put("GCT", 'A')
            put("TCC", 'S')
            put("CCC", 'P')
            put("ACC", 'T')
            put("GCC", 'A')
            put("TCA", 'S')
            put("CCA", 'P')
            put("ACA", 'T')
            put("GCA", 'A')
            put("TCG", 'S')
            put("CCG", 'P')
            put("ACG", 'T')
            put("GCG", 'A')
            put("TAT", 'Y')
            put("CAT", 'H')
            put("AAT", 'N')
            put("GAT", 'D')
            put("TAC", 'Y')
            put("CAC", 'H')
            put("AAC", 'N')
            put("GAC", 'D')
            put("TAA", null)
            put("CAA", 'Q')
            put("AAA", 'K')
            put("GAA", 'E')
            put("TAG", null)
            put("CAG", 'Q')
            put("AAG", 'K')
            put("GAG", 'E')
            put("TGT", 'C')
            put("CGT", 'R')
            put("AGT", 'S')
            put("GGT", 'G')
            put("TGC", 'C')
            put("CGC", 'R')
            put("AGC", 'S')
            put("GGC", 'G')
            put("TGA", null)
            put("CGA", 'R')
            put("AGA", 'R')
            put("GGA", 'G')
            put("TGG", 'W')
            put("CGG", 'R')
            put("AGG", 'R')
            put("GGG", 'G')
        }
    }
    private val COMPLEMENT = object : HashMap<Character, Character>() {
        init {
            put('A', 'T')
            put('C', 'G')
            put('G', 'C')
            put('T', 'A')
        }
    }

    private fun translate(dna: String): Set<String> {
        val orfs = HashSet()
        var orf: StringBuilder? = null
        var i = 0
        while (i < dna.length() / 3) {
            if (dna.substring(i * 3, i * 3 + 3).equals("ATG")) {
                orf = StringBuilder()
                var aa: Character? = null
                while (i < dna.length() / 3 && (aa = CODON.get(dna.substring(i * 3, i * 3 + 3))) != null) {
                    orf!!.append(aa)
                    i++
                }
                if (aa == null)
                    for (j in 0 until orf!!.length())
                        if (orf!!.charAt(j) === 'M')
                            orfs.add(orf!!.substring(j))
            }
            i++
        }
        return orfs
    }

    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val stdout = System.out
        stdin.next()
        var seq = ""
        while (stdin.hasNext())
            seq += stdin.next()
        val rev = StringBuilder(seq).reverse().chars().mapToObj({ x -> x }).map(???({ COMPLEMENT.get() })).map(???({ String.valueOf() })).collect(Collectors.joining())
        val orfs = HashSet()
        for (i in 0..2) {
            orfs.addAll(translate(seq.substring(i)))
            orfs.addAll(translate(rev.substring(i)))
        }
        orfs.forEach(???({ stdout.println() }))
        stdin.close()
    }
}
