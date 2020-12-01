package leetcode

fun change(amount: Int, coins: IntArray): Int {
    val cache = IntArray(amount + 1)
    cache[0] = 1
    for (coin in coins) {
        for (i in 0 until amount) {
            if (coin + i > amount) break
            cache[coin + i] += cache[i]
        }
    }
    return cache[amount]
}
