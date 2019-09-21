private fun recur(candidates: IntArray, target: Int): List<List<Int>> {
    val res = mutableListOf<List<Int>>()
    if (candidates.isEmpty())
        return res
    for ((i, candidate) in candidates.withIndex()) {
        if (target < candidate)
            break
        else if (target == candidate) {
            res.add(listOf(candidate))
            break
        }
        val lst = combinationSum(
            candidates.sliceArray(i until candidates.size),
            target - candidate
        )
        lst.forEach { res.add(listOf(candidate) + it) }
    }
    return res
}

fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
    candidates.sort()
    return recur(candidates, target)
}
