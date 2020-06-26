pub fn plus_one(mut digits: Vec<i32>) -> Vec<i32> {
    let last_idx = digits.len() - 1;
    digits[last_idx] += 1;
    for idx in (1 .. digits.len()).rev() {
        if digits[idx] >= 10 {
            digits[idx] -= 10;
            digits[idx - 1] += 1;
        } else {
            break;
        }
    }
    if digits[0] >= 10 {
        digits[0] -= 10;
        digits.insert(0, 1);
    }
    return digits;
}
