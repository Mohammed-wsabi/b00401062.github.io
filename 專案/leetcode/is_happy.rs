use std::collections::HashSet;

pub fn is_happy(n: i32) -> bool {
    let mut curr = n;
    let seen = &mut HashSet::new();
    while curr != 1 && !seen.contains(&curr){
        seen.insert(curr);
        let mut next = 0;
        while curr != 0 {
            let digit = curr % 10;
            curr /= 10;
            next += digit * digit;
        }
        curr = next;
    }
    return curr == 1;
}
