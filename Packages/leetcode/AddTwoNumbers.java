class Solution {
	private void carry(ListNode l) {
		if (l.val < 10)
			return;
		l.val %= 10;
		if (l.next == null)
			l.next = new ListNode(1);
		else {
			l.next.val++;
			carry(l.next);
		}
	}
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		if (l1 == null)
			return l2;
		if (l2 == null)
			return l1;
		ListNode node = new ListNode(l1.val + l2.val);
		node.next = addTwoNumbers(l1.next, l2.next);
		carry(node);
		return node;
	}
}
