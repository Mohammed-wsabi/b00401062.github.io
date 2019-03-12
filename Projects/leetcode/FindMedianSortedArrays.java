class Solution {
	private static double select(List<Integer> l1, List<Integer> l2, int k) {
		if (l1.size() == 0) return l2.get(k);
		if (l2.size() == 0) return l1.get(k);
		int pivot = l1.get(l1.size()/2);
		int index = Collections.binarySearch(l2, pivot);
		int lt1 = l1.size()/2, lt2 = (index >= 0 ? index : -index-1), lt = lt1 + lt2;
		if (k == lt) return pivot;
		else if (k < lt) return select(l1.subList(0, lt1), l2.subList(0, lt2), k);
		else return select(l1.subList(lt1+1, l1.size()), l2.subList(lt2, l2.size()), k-lt-1);
	}
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		List<Integer> l1 = Arrays.stream(nums1).boxed().collect(Collectors.toList());
		List<Integer> l2 = Arrays.stream(nums2).boxed().collect(Collectors.toList());
		int len = nums1.length + nums2.length;
		return len % 2 == 0 ? (select(l1, l2, len/2-1) + select(l1, l2, len/2))/2 : select(l1, l2, len/2);
	}
}
