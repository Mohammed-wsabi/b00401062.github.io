/*
ID: rombin82
LANG: JAVA
TASK: gift1
*/

import java.io.*
import java.util.*

object gift1 {
    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(File("gift1.in"))
        val stdout = PrintWriter(File("gift1.out"))
        Runtime.getRuntime().addShutdownHook(Thread { stdout.flush() })
        val name_cnt = stdin.nextInt()
        val names = ArrayList<String>(name_cnt)
        val table = HashMap<String, Integer>(name_cnt)
        for (i in 0 until name_cnt) {
            val name = stdin.next()
            names.add(name)
            table.put(name, 0)
        }
        for (i in 0 until name_cnt) {
            val gvr_name = stdin.next()
            val money = stdin.nextInt()
            val rcv_cnt = stdin.nextInt()
            if (rcv_cnt == 0)
                continue
            table.put(gvr_name, table.get(gvr_name) - money / rcv_cnt * rcv_cnt)
            for (j in 0 until rcv_cnt) {
                val rcv_name = stdin.next()
                table.put(rcv_name, table.get(rcv_name) + money / rcv_cnt)
            }
        }
        for (name in names)
            stdout.println(name + " " + table.get(name))
    }
}
