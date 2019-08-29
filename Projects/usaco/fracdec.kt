/*
ID: rombin82
LANG: JAVA
TASK: fracdec
*/

import java.io.*
import java.util.*
import java.util.stream.Collectors

object fracdec {
    private class Tuple internal constructor(internal var order: Int, internal var quotient: Int)

    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(File("fracdec.in"))
        val stdout = PrintWriter(File("fracdec.out"))
        val quotient = StringBuffer()
        Runtime.getRuntime().addShutdownHook(Thread {
            var i = 0
            while (i < quotient.length() - 76) {
                stdout.println(quotient.substring(i, i + 76))
                i += 76
            }
            stdout.println(quotient.substring(quotient.length() / 76 * 76))
            stdout.flush()
        })
        val numerator = stdin.nextInt()
        val denominator = stdin.nextInt()
        quotient.append(numerator / denominator)
        quotient.append('.')
        var order = 0
        var remainder = numerator % denominator
        if (remainder == 0) {
            quotient.append(0)
            return
        }
        val records = arrayOfNulls<Tuple>(denominator)
        while (remainder != 0 && records[remainder] == null) {
            records[remainder] = Tuple(order++, remainder * 10 / denominator)
            remainder = remainder * 10 % denominator
        }
        val digits = Arrays.stream(records).filter(???({ Objects.nonNull() })).sorted(Comparator.comparing(???({ it.getOrder() }))).toArray(Tuple[]::new  /* Currently unsupported in Kotlin */)
        if (remainder == 0) {
            quotient.append(Arrays.stream(digits).mapToInt(???({ it.getQuotient() })).mapToObj(???({ Integer.toString() })).collect(Collectors.joining("")))
            return
        }
        val start = records[remainder].order
        Arrays.stream(digits).limit(start).forEach({ digit -> quotient.append(digit.getQuotient()) })
        quotient.append('(')
        Arrays.stream(digits).skip(start).forEach({ digit -> quotient.append(digit.getQuotient()) })
        quotient.append(')')
    }
}
