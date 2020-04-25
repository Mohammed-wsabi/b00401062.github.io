pub fn move_zeroes(nums: &mut Vec<i32>) {
    let mut non_zero_count = 0;
    for idx in 0 .. nums.len() {
        let num = nums[idx];
        if num == 0 { continue; }
        nums[non_zero_count] = num;
        non_zero_count += 1;
    }
    for num in nums.iter_mut().skip(non_zero_count) {
        *num = 0;
    }
}
