package Array;


/**
 * LeetCode problem 80    Remove Duplicates from Sorted Array II
 *
 *
 * Given a sorted array nums, remove the duplicates in-place
 * such that duplicates appeared at most twice and return the new length.
 *
 * Do not allocate extra space for another array;
 * you must do this by modifying the input array in-place with O(1) extra memory.
 *
 *
 * Clarification:
 *
 * Confused why the returned value is an integer, but your answer is an array?
 *
 * Note that the input array is passed in by reference,
 * which means a modification to the input array will be known to the caller.
 *
 * Internally you can think of this:
 *
 * //nums is passed in by reference. (i.e., without making a copy)
 * //any modification to nums in your function would be known by the caller.
 * //using the length returned by your function, it prints the first len elements.
 * int len = removeDuplicates(nums);
 * for (int i = 0; i < len; i++) {
 *     print(nums[i]);
 * }
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,2,2,3]
 * Output: 5, nums = [1,1,2,2,3]
 * Explanation: Your function should return length = 5,
 *              with the first five elements of nums being
 *              1, 1, 2, 2 and 3 respectively.
 *              It doesn't matter what you leave beyond the returned length.
 *
 *
 * Example 2:
 *
 * Input: nums = [0,0,1,1,1,1,2,3,3]
 * Output: 7, nums = [0,0,1,1,2,3,3]
 * Explanation: Your function should return length = 7,
 *              with the first seven elements of nums being modified to
 *              0, 0, 1, 1, 2, 3 and 3 respectively.
 *              It doesn't matter what values are set beyond the returned length.
 *
 *
 * Constraints:
 *      0 <= nums.length <= 3 * 104
 *     -104 <= nums[i] <= 104
 *      nums is sorted in ascending order.
 */
public class Pro80_RemoveDuplicatesFromSortedArrayII {

    /**
     * 要有一个 bool值记录是否已经重复，
     * 但是该方法解决不了允许3(及以上)个重复
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int i = 0;
        boolean hasOne = false;
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] < nums[j]) {
                nums[++i] = nums[j];
                hasOne = true;
            } else if (hasOne) {
                nums[++i] = nums[j];
                hasOne = false;
            } else if (j == 1) {
                ++i;
            }
        }

        return i + 1;
    }

    /**
     * 使用cnt计数试一下
     * [0,0,0,1,1,1]
     */
    public int removeDuplicates2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int i = 1, cnt = 1;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] == nums[j - 1]) {
                ++cnt; //有重复，计数加1
            } else {
                cnt = 1; //不重复，计数重置
            }

            if (cnt <= 2) {
                nums[i++] = nums[j];
            }
        }

        return i;
    }

}
