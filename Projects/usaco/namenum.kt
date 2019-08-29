/*
ID: rombin82
LANG: JAVA
TASK: namenum
*/

import java.io.*
import java.util.*

object namenum {
    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val fin1 = Scanner(File("namenum.in"))
        val fin2 = Scanner(File("dict.txt"))
        val stdout = PrintWriter(File("namenum.out"))
        Runtime.getRuntime().addShutdownHook(Thread { stdout.flush() })
        val map = CharArray(256)
        map['C'] = '2'
        map['B'] = map['C']
        map['A'] = map['B']
        map['F'] = '3'
        map['E'] = map['F']
        map['D'] = map['E']
        map['I'] = '4'
        map['H'] = map['I']
        map['G'] = map['H']
        map['L'] = '5'
        map['K'] = map['L']
        map['J'] = map['K']
        map['O'] = '6'
        map['N'] = map['O']
        map['M'] = map['N']
        map['S'] = '7'
        map['R'] = map['S']
        map['P'] = map['R']
        map['V'] = '8'
        map['U'] = map['V']
        map['T'] = map['U']
        map['Y'] = '9'
        map['X'] = map['Y']
        map['W'] = map['X']
        val num = fin1.next()
        val num_len = num.length()
        var flag = false
        while (fin2.hasNext()) {
            val word = fin2.next()
            if (word.length() !== num_len)
                continue
            for (i in 0 until num_len) {
                if (map[word.charAt(i)] != num.charAt(i))
                    break
                if (i == num_len - 1) {
                    flag = true
                    stdout.println(word)
                }
            }
        }
        if (!flag)
            stdout.println("NONE")
    }
}
