package Array;

/**
 * LeetCode problem 27     Remove Element
 *
 *
 * Given an array nums and a value val, remove all instances
 * of that value in-place and return the new length.
 *
 * Do not allocate extra space for another array,
 * you must do this by modifying the input array in-place with O(1) extra memory.
 *
 * The order of elements can be changed.
 * It doesn't matter what you leave beyond the new length.
 *
 *
 *
 * Clarification:
 *
 * Confused why the returned value is an integer but your answer is an array?
 *
 * Note that the input array is passed in by reference,
 * which means a modification to the input array will be known to the caller as well.
 *
 * Internally you can think of this:
 *
 * // nums is passed in by reference. (i.e., without making a copy)
 * // any modification to nums in your function would be known by the caller.
 * // using the length returned by your function, it prints the first len elements.
 * int len = removeElement(nums, val);
 * for (int i = 0; i < len; i++) {
 *     print(nums[i]);
 * }
 *
 *
 * Example 1:
 *
 * Input: nums = [3,2,2,3], val = 3
 * Output: 2, nums = [2,2]
 * Explanation: Your function should return length = 2,
 *              with the first two elements of nums being 2.
 *              It doesn't matter what you leave beyond the returned length.
 *              For example if you return 2 with nums = [2,2,3,3] or
 *              nums = [2,3,0,0], your answer will be accepted.
 *
 *
 * Example 2:
 *
 * Input: nums = [0,1,2,2,3,0,4,2], val = 2
 * Output: 5, nums = [0,1,4,0,3]
 * Explanation: Your function should return length = 5,
 *              with the first five elements of nums containing 0, 1, 3, 0, and 4.
 *              Note that the order of those five elements can be arbitrary.
 *              It doesn't matter what values are set beyond the returned length.
 *
 *
 * Constraints:
 *      0 <= nums.length <= 100
 *      0 <= nums[i] <= 50
 *      0 <= val <= 100
 */
public class Pro27_RemoveElement {


    /**
     * 双指针 - 快慢扫描法
     * 对整个数组进行扫描，把 !=val的值，都交换到数组前面去
     */
    public int removeElement(int[] nums, int val) {
        int index = 0;
        for (int i=0; i<nums.length; i++) {
            if (nums[i] != val)
                nums[index++] = nums[i];
        }
        return index;
    }


    /**
     * 双指针 - 左右逼近法
     * 这道题本身不难，从节省交换次数出发思考
     */
    public int removeElement2(int[] nums, int val) {
        if (nums.length == 0) {
            return 0;
        }

        int l = 0, r = nums.length - 1;
        while (l <= r) {
            if (nums[l] != val) {
                ++l;
            } else if (nums[r] == val) {
                --r;
            } else {
                int tmp = nums[l];
                nums[l] = nums[r];
                nums[r] = tmp;

                //不创建 tmp 法
                //nums[l] = nums[l] ^ nums[r];
                //nums[r] = nums[l] ^ nums[r];
                //nums[l] = nums[l] ^ nums[r];
                ++l;
                --r;
            }
        }
        return l;
    }


}
