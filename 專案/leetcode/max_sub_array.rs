use std::cmp::max;

pub fn max_sub_array(nums: Vec<i32>) -> i32 {
    let mut cur_max_sum = nums[0];
    let mut max_sum = nums[0];
    for &num in &nums[1..] {
        cur_max_sum = max(num, cur_max_sum + num);
        max_sum = max(max_sum, cur_max_sum);
    }
    return max_sum;
}
