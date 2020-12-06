package Array;


import java.util.Arrays;

/**
 * LeetCode problem 16   3Sum Closest
 *
 * Given an array nums of n integers and an integer target,
 * find three integers in nums such that the sum is closest to target.
 * Return the sum of the three integers.
 * You may assume that each input would have exactly one solution.
 *
 *
 * Example 1:
 *
 * Input: nums = [-1,2,1,-4], target = 1
 * Output: 2
 * Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 *
 *
 * Constraints:
 *      3 <= nums.length <= 10^3
 *     -10^3 <= nums[i] <= 10^3
 *     -10^4 <= target <= 10^4
 */
public class Pro16_3SumClosest {


    public int threeSumClosest(int[] nums, int target) {
        int result = 1000; //规定最大值

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue; //跳过重复计算i
            }

            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];

                if (sum == target) {
                    return sum; //没有比相等更接近
                }

                if (Math.abs(sum - target) < Math.abs(result - target)) {
                    result = sum; //有更接近的，则更新
                }

                if (sum < target) {
                    ++l;
                } else {
                    --r;
                }

            }
        }
        return result;
    }


    public static void main(String[] args) {
        Pro16_3SumClosest solution = new Pro16_3SumClosest();

        //System.out.println(solution.threeSumClosest(new int[] {-1,2,1,-4}, 1));
        //System.out.println(solution.threeSumClosest(new int[] {0,2,1,-3}, 1));
        //System.out.println(solution.threeSumClosest(new int[] {-3,-2,-5,3,-4}, -1));
        System.out.println(solution.threeSumClosest(new int[] {-100,-98,-2,-1}, -101));
    }

}
