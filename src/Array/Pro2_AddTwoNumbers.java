package Array;

/**
 * LeetCode problem 2   Add Two Numbers
 * <p>
 * You are given two non-empty linked lists
 * representing two non-negative integers.
 * The digits are stored in reverse order,
 * and each of their nodes contains a single digit.
 * Add the two numbers and return the sum as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * Input: l1 = [0], l2 = [0]
 * Output: [0]
 * <p>
 * <p>
 * Example 3:
 * <p>
 * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * Output: [8,9,9,9,0,0,0,1]
 *
 *
 * Constraints:
 *
 * The number of nodes in each linked list is in the range [1, 100].
 * 0 <= Node.val <= 9
 * It is guaranteed that the list represents a number that does not have leading zeros.
 *
 */
public class Pro2_AddTwoNumbers {

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode result = null;
        ListNode nowNode = null;
        while (l1 != null && l2 != null) {
            int val = l1.val + l2.val + carry;
            if (val > 9) {
                carry = 1;
                val = val - 10;
            } else {
                carry = 0;
            }
            ListNode tmp = new ListNode(val, null);
            if (nowNode == null) {
                nowNode = tmp;
                result = nowNode;
            } else {
                nowNode.next = tmp;
                nowNode = tmp;
            }
            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null) {
            int val = l1.val + carry;
            if (val > 9) {
                carry = 1;
                val = val - 10;
            } else {
                carry = 0;
            }
            ListNode tmp = new ListNode(val, null);
            if (nowNode == null) {
                nowNode = tmp;
                result = nowNode;
            } else {
                nowNode.next = tmp;
                nowNode = tmp;
            }
            l1 = l1.next;
        }

        while (l2 != null) {
            int val = l2.val + carry;
            if (val > 9) {
                carry = 1;
                val = val - 10;
            } else {
                carry = 0;
            }
            ListNode tmp = new ListNode(val, null);
            if (nowNode == null) {
                nowNode = tmp;
                result = nowNode;
            } else {
                nowNode.next = tmp;
                nowNode = tmp;
            }
            l2 = l2.next;
        }

        if (carry > 0) {
            nowNode.next = new ListNode(1, null);
        }

        return result;
    }

    /**
     * 和方法1完全相同，只缩减了代码行数
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode fakeHead = new ListNode(0);

        ListNode nowNode = fakeHead;
        while (l1 != null || l2 != null) {
            int v1 = (l1 != null) ? l1.val : 0;
            int v2 = (l2 != null) ? l2.val : 0;
            int sum = v1 + v2 + carry;
            carry = sum / 10;

            nowNode.next = new ListNode(sum % 10, null);
            nowNode = nowNode.next;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        if (carry > 0) {
            nowNode.next = new ListNode(1, null);
        }

        return fakeHead.next;
    }

    /**
     * 递归写法
     */
    public ListNode addTwoNumbers3(ListNode l1, ListNode l2) {
        return getNextNode(l1, l2, 0);
    }

    private ListNode getNextNode(ListNode l1, ListNode l2, int carry) {
        if (l1 == null && l2 == null) {
            if (carry > 0) {
                return new ListNode(1);
            } else {
                return null;
            }
        }
        int v1 = (l1 != null) ? l1.val : 0;
        int v2 = (l2 != null) ? l2.val : 0;
        int sum = v1 + v2 + carry;

        ListNode nowNode = new ListNode(sum % 10);

        ListNode l1Next = (l1 != null) ? l1.next : null;
        ListNode l2Next = (l2 != null) ? l2.next : null;

        nowNode.next = getNextNode(l1Next, l2Next, sum / 10);
        return nowNode;
    }





    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}
