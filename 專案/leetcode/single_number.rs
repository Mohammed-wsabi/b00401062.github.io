pub fn single_number(nums: Vec<i32>) -> i32 {
    let mut res = 0;
    for num in &nums {
        res = res ^ num;
    }
    return res;
}
