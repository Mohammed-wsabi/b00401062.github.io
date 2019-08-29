import java.io.*
import java.util.*
import java.util.stream.Collectors

internal object UCL {
    private class Team {
        var point = 0
            private set
        var goal = 0
            private set

        fun plus(point: Int, goal: Int) {
            this.point += point
            this.goal += goal
        }
    }

    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        var t = Integer.parseInt(stdin.nextLine())
        while (t-- > 0) {
            val teams = HashMap()
            for (i in 0..11) {
                val tokens = stdin.nextLine().split(" ")
                if (!teams.containsKey(tokens[0]))
                    teams.put(tokens[0], Team())
                if (!teams.containsKey(tokens[4]))
                    teams.put(tokens[4], Team())
                val diff = Integer.parseInt(tokens[1]) - Integer.parseInt(tokens[3])
                if (diff > 0)
                    teams.get(tokens[0]).plus(3, 0)
                else if (diff < 0)
                    teams.get(tokens[4]).plus(3, 0)
                else {
                    teams.get(tokens[0]).plus(1, 0)
                    teams.get(tokens[4]).plus(1, 0)
                }
                teams.get(tokens[0]).plus(0, diff)
                teams.get(tokens[4]).plus(0, -diff)
            }
            System.out.println(
                    teams.entrySet()
                            .stream()
                            .sorted(
                                    Map.Entry.comparingByValue(
                                            Comparator.comparingInt(???{ it.getPoint() })
                    .thenComparingInt(???({ it.getGoal() }))
            .reversed()
            )
            )
            .limit(2)
                    .map(???({ Map.Entry.getKey() }))
            .collect(Collectors.joining(" "))
            )
        }
    }
}
