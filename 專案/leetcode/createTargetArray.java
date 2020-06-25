package leetcode;

public int[] CreateTargetArray(int[] nums, int[] index) {
    List<Integer> res = new ArrayList<>();
    for (int i = 0; i < nums.length; i++) {
        res.add(index[i], nums[i]);
    }
    return res.stream().mapToInt(Integer::intValue).toArray();
}
