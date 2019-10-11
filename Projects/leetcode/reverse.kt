package leetcode;

fun reverse(x: Int): Int {
    var _x = x
    var y = 0
    while (_x != 0) {
        val pop = _x % 10
        _x /= 10
        if (
            y > Int.MAX_VALUE / 10
            || y == Int.MAX_VALUE / 10 && pop > 7
            || y < Int.MIN_VALUE / 10
            || y == Int.MIN_VALUE / 10 && pop < -8
        ) return 0
        y = y * 10 + pop
    }
    return y
}
