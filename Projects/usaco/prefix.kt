/*
ID: rombin82
LANG: JAVA
TASK: prefix
*/

import java.io.*
import java.util.*
import java.util.stream.Collectors

object prefix {
    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(File("prefix.in"))
        val stdout = PrintWriter(File("prefix.out"))
        Runtime.getRuntime().addShutdownHook(Thread { stdout.flush() })
        var line: String
        var sequence = ""
        val primitives = ArrayList()
        while (!(line = stdin.nextLine().trim()).equals("."))
            primitives.addAll(Arrays.asList(line.split(" ")))
        while (stdin.hasNext())
            sequence += stdin.next()
        val seqlen = sequence.length()
        val counts = IntArray(seqlen + 1)
        val lengths = primitives.stream().map(???({ String.length() })).collect(Collectors.toSet())
        for (i in seqlen - 1 downTo 0)
            for (l in lengths)
                if (l <= seqlen - i && primitives.contains(sequence.substring(i, i + l)))
                    counts[i] = Math.max(counts[i], l + counts[i + l])
        stdout.println(counts[0])
    }
}
