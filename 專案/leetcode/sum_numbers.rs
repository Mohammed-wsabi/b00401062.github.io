use std::cell::RefCell;
use std::rc::Rc;

pub fn sum_numbers(root: Option<Rc<RefCell<TreeNode>>>) -> i32 {
    return traverse(&root, 0);
}

fn traverse(root: &Option<Rc<RefCell<TreeNode>>>, root_sum: i32) -> i32 {
    if root.is_none() {
        return 0;
    }
    let node = root.as_ref().unwrap().borrow();
    let curr_sum = root_sum * 10 + node.val;
    if node.left.is_none() && node.right.is_none() {
        return curr_sum;
    }
    let left_sum = traverse(&node.left, curr_sum);
    let right_sum = traverse(&node.right, curr_sum);
    return left_sum + right_sum;
}
