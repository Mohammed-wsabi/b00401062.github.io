package leetcode;

class SortList {
    private static int size(ListNode head) {
        int size = 0;
        for (ListNode ptr = head; ptr != null; ptr = ptr.next) size++;
        return size;
    }

    private static ListNode get(ListNode head, int n) {
        while (n-- > 0) {
            head = head.next;
        }
        return head;
    }

    private static ListNode merge(ListNode tail, int m, int r) {
        final ListNode lNode = tail.next;
        final ListNode mNode = get(lNode, m);
        final ListNode rNode = get(mNode, r - m);
        ListNode lPtr = lNode;
        ListNode mPtr = mNode;
        while (lPtr != mNode || mPtr != rNode) {
            if (mPtr == rNode || (lPtr != mNode && lPtr.val <= mPtr.val)) {
                tail.next = lPtr;
                lPtr = lPtr.next;
            } else {
                tail.next = mPtr;
                mPtr = mPtr.next;
            }
            tail = tail.next;
            tail.next = null;
        }
        tail.next = rNode;
        return tail;
    }

    public static ListNode sortList(ListNode head) {
        final int size = size(head);
        final ListNode dummyHead = new ListNode(0, head);
        for (int width = 1; width < size; width *= 2) {
            ListNode tail = dummyHead;
            for (int l = 0; l < size; l += 2 * width) {
                final int m = Math.min(l + width, size) - l;
                final int r = Math.min(l + 2 * width, size) - l;
                tail = merge(tail, m, r);
            }
        }
        return dummyHead.next;
    }
}
