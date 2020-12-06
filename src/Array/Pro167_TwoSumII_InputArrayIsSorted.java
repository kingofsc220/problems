package Array;


import java.util.Arrays;

/**
 * LeetCode problem 2   Two Sum II - Input array is sorted
 *
 *
 * Given an array of integers that is already sorted in ascending order,
 * find two numbers such that they add up to a specific target number.
 *
 * The function twoSum should return indices
 * of the two numbers such that they add up to the target,
 * where index1 must be less than index2.
 *
 *
 * Note:
 *
 * Your returned answers (both index1 and index2) are not zero-based.
 * You may assume that each input would have exactly one solution
 * and you may not use the same element twice.
 *
 *
 * Example 1:
 *
 * Input: numbers = [2,7,11,15], target = 9
 * Output: [1,2]
 * Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.
 *
 *
 * Example 2:
 *
 * Input: numbers = [2,3,4], target = 6
 * Output: [1,3]
 *
 *
 * Example 3:
 *
 * Input: numbers = [-1,0], target = -1
 * Output: [1,2]
 *
 *
 * Constraints:
 *
 *      2 <= nums.length <= 3 * 10^4
 *      -1000 <= nums[i] <= 1000
 *      nums is sorted in increasing order.
 *      -1000 <= target <= 1000
 */
public class Pro167_TwoSumII_InputArrayIsSorted {

    /**
     * 已排序，是问题的关键
     *
     * 例如: [1,2,3,4,5,6,7,8,9] tar:10
     * 则: 1+9，2+8，3+7，4+6
     * 左边的数越来越大，而右边的数越来越小，
     * 所以大致思路应该是用两个指针往中间移动的过程，能O(n)完成
     */
    public int[] twoSum(int[] nums, int target) {
        int l = 0; //左指针
        int r = nums.length - 1; //右指针

        while (l < r) {
            int needVal = target - nums[l]; //左边值需要的值

            if (needVal == nums[r]) {
                return new int[] {++l, ++r}; //1起始
            } else if (needVal < nums[r]) {
                --r;
            } else {
                ++l;
            }
        }
        return new int[2];
    }



    public static void main(String[] args) {
        Pro167_TwoSumII_InputArrayIsSorted Solution = new Pro167_TwoSumII_InputArrayIsSorted();

        int [] a = new int[] {2,3,4};
        System.out.println(Arrays.toString(Solution.twoSum(a, 6)));

    }
}
