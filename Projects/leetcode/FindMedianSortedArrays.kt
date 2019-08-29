internal class Solution {
    private fun select(l1: List<Integer>, l2: List<Integer>, k: Int): Double {
        if (l1.size() === 0) return l2[k]
        if (l2.size() === 0) return l1[k]
        val pivot = l1[l1.size() / 2]
        val index = Collections.binarySearch(l2, pivot)
        val lt1 = l1.size() / 2
        val lt2 = if (index >= 0) index else -index - 1
        val lt = lt1 + lt2
        return if (k == lt)
            pivot.toDouble()
        else if (k < lt)
            select(l1.subList(0, lt1), l2.subList(0, lt2), k)
        else
            select(l1.subList(lt1 + 1, l1.size()), l2.subList(lt2, l2.size()), k - lt - 1)
    }

    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        val l1 = Arrays.stream(nums1).boxed().collect(Collectors.toList())
        val l2 = Arrays.stream(nums2).boxed().collect(Collectors.toList())
        val len = nums1.size + nums2.size
        return if (len % 2 == 0) (select(l1, l2, len / 2 - 1) + select(l1, l2, len / 2)) / 2 else select(l1, l2, len / 2)
    }
}
