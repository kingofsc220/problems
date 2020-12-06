package Array;


import java.util.*;

/**
 * LeetCode problem 15   3Sum
 *
 *
 * Given an array nums of n integers,
 * are there elements a, b, c in nums such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 *
 * Notice that the solution set must not contain duplicate triplets.
 *
 *
 * Example 1:
 *
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 *
 *
 * Example 2:
 *
 * Input: nums = []
 * Output: []
 *
 *
 * Example 3:
 *
 * Input: nums = [0]
 * Output: []
 *
 *
 * Constraints:
 *      0 <= nums.length <= 3000
 *     -105 <= nums[i] <= 105
 */
public class Pro15_3Sum {


    /**
     * 3个数的查找，想要 O(n)结束战斗肯定是不可能了，
     * 那就可以先排序，再想办法了
     *
     * 排序后，应该可以用{@link Pro167_TwoSumII_InputArrayIsSorted}试试
     */
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }

        Arrays.sort(nums); //排序 O(n*log(n))

        // [-4,-1,-1,0,1,2]
        Set<List<Integer>> set = new HashSet<>();
        for (int i = 0; i < nums.length - 2; i++) {
            int l = i + 1;
            int r = nums.length - 1;

            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (0 == sum) {
                    //添加到set后，仍要继续搜索其他解
                    set.add(Arrays.asList(nums[i], nums[l++], nums[r--]));
                } else if (0 < sum) {
                    --r;
                } else {
                    ++l;
                }
            }
        }

        return new ArrayList<>(set);
    }


    /**
     * 有没有什么可以优化的？
     * 局部优化，主要是从无效计算下手
     */
    public List<List<Integer>> threeSum2(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }

        Arrays.sort(nums);

        Set<List<Integer>> set = new HashSet<>();
        for (int i = 0; i < nums.length - 2; i++) {
            int l = i + 1;
            int r = nums.length - 1;

            if (nums[i] > 0) {
                break; //nums[i]>0，则sum肯定>0
            }
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue; //前面 i-1时已经算过了
            }


            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (0 == sum) {
                    //添加到set后，仍要继续搜索其他解
                    set.add(Arrays.asList(nums[i], nums[l++], nums[r--]));
                    while (l < r && nums[l] == nums[l - 1])
                        ++l; //前面 l-1时已经算过了
                    while (l < r && nums[r] == nums[r + 1])
                        --r; //前面 r+1时已经算过了
                } else if (0 < sum) {
                    --r;
                } else {
                    ++l;
                }
            }
        }

        return new ArrayList<>(set);
    }

}
